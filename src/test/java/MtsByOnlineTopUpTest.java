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
    public void testBlockTitle() {
        mainPage.scrollToPaymentBlock();

        WebElement title = mainPage.getBlockTitle();

        Assertions.assertTrue(title.getText().contains("Онлайн пополнение\nбез комиссии"),
                "Название блока не найдено или некорректное");
    }

    @Test
    public void testPaymentSystemLogosPresence() {
        mainPage.scrollToPaymentBlock();

        WebElement section = mainPage.getPaymentSection();

        java.util.List<WebElement> logos = section.findElements(By.xpath("//div[@class=\"pay__partners\"]//img"));

        Assertions.assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены");
    }

    @Test
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

    //@ParameterizedTest
   // @EnumSource(CommunicationServices.class)
   // public void testFormFillCommunicationServices(CommunicationServices option) {
      //  mainPage.scrollToPaymentBlock();
      //  mainPage.selectCommunicationServices(option);
//как сделать тест чтобы он запускался с параметрами (data source)
        //как сделать параметриззированный тест в junit
    }

