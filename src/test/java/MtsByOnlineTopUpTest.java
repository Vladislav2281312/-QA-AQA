import enums.CommunicationServices;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.MainPage;
import views.PaymentView;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MtsByOnlineTopUpTest {

    private WebDriver driver;
    private MainPage mainPage;
    private PaymentView paymentView;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        // экземпляр ChromeDriver без указания пути
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("https://mts.by");

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка названия блока \"Онлайн пополнение без комиссии\"")
    public void testBlockTitle() {
        mainPage.scrollToPaymentBlock();

        WebElement title = mainPage.getBlockTitle();

        Assertions.assertTrue(title.getText().contains("Онлайн пополнение\nбез комиссии"),
                "Название блока не найдено или некорректное");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogosPresence() {
        mainPage.scrollToPaymentBlock();

        WebElement section = mainPage.getPaymentSection();

        java.util.List<WebElement> logos = section.findElements(By.xpath("//div[@class=\"pay__partners\"]//img"));

        Assertions.assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены");
    }

    @Test
    @DisplayName("Проверка работы ссылки \"Подробнее о сервисе\"")
    public void testMoreDetailsLink() {
        mainPage.scrollToPaymentBlock();

        WebElement link = mainPage.getMoreDetailsLink();

        String originalUrl = driver.getCurrentUrl();

        link.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));

        String newUrl = driver.getCurrentUrl();

        Assertions.assertTrue(newUrl.contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка ведёт не на ожидаемую страницу");

        WebElement payCardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(text(),\"Оплата банковской картой\")]")
        ));
        Assertions.assertTrue(payCardTitle.isDisplayed(),
                "Блок оплаты банковской картой не отображается");
    }

    @Test
    @DisplayName("Проверка работы кнопки \"Продолжить\"")
    public void testFormFillAndContinueButton() {
        mainPage.scrollToPaymentBlock();
        mainPage.selectCommunicationServices(CommunicationServices.PHONE_SERVICES);

        mainPage.fillInfoData("297777777");
        mainPage.fillSum("20");

        Assertions.assertTrue( mainPage.isContinueButtonEnabled(), "Кнопка 'Продолжить' недоступна");

        mainPage.clickContinue();

        paymentView = new PaymentView(driver);

        wait.until(ExpectedConditions.visibilityOf(paymentView.getPaymentIframe()));

        paymentView.switchToPaymentFrame();

        Assertions.assertTrue(paymentView.isPaymentFormDisplayed(), "Окошко оплаты не появилось");

        // Возвращаемся из iframe, если нужно дальше взаимодействовать с основной страницей
        driver.switchTo().defaultContent();
    }
    @ParameterizedTest
    @EnumSource(CommunicationServices.class)
    @DisplayName("Проверка placeholder в форме заполнения данных об оплате")
    public void testFormFillCommunicationServices(CommunicationServices service) throws InterruptedException {
        // Инициализация HashMap с помощью анонимного блока без цикла
        Map<CommunicationServices, String> mapInfoTextValues = new HashMap() {{
            put(CommunicationServices.PHONE_SERVICES, "Номер телефона");
            put(CommunicationServices.HOME_INTERNET, "Номер абонента");
            put(CommunicationServices.INSTALLMENT_PLAN, "Номер счета на 44");
            put(CommunicationServices.DEBT, "Номер счета на 2073");
        }};

        mainPage.scrollToPaymentBlock();
        mainPage.selectCommunicationServices(service);

        Assertions.assertEquals("Сумма", mainPage.getSumPlaceholder());
        Assertions.assertEquals(mapInfoTextValues.get(service), mainPage.getNumberPlaceholder());
        Assertions.assertEquals("E-mail для отправки чека", mainPage.getEmailPlaceholder());

    }

}



