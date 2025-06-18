import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsByOnlineTopUpTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()=\"Принять\"]")
            ));
            acceptCookiesButton.click();
        } catch (TimeoutException e) {
            // Окно не появилось — продолжаем
        }

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Проверка названия блока «Онлайн пополнение без комиссии»

    @Test
    public void testBlockTitle() {
        scrollToPayment();
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),\"Онлайн пополнение\")]")
        ));
        Assertions.assertTrue(blockTitle.getText().contains("Онлайн пополнение\n" +
                        "без комиссии"),
                "Название блока не найдено или некорректное");
    }

    //Проверка наличия логотипов платёжных систем внутри блока

    @Test
    public void testPaymentSystemLogosPresence() {
        scrollToPayment();
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
        ));


        java.util.List<WebElement> logos = block.findElements(By.xpath("//div[@class=\"pay__partners\"]//img"));

        Assertions.assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены");


    }

    //Проверка работы ссылки «Подробнее о сервисе»

    @Test
    public void testMoreDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        ));

        String originalUrl = driver.getCurrentUrl();

        link.click();

        // Ждём перехода или появления новой страницы/контента
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));

        String newUrl = driver.getCurrentUrl();

        Assertions.assertTrue(newUrl.contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"), "Ссылка ведёт не на ожидаемую страницу");

        WebElement payCardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(text(),\"Оплата банковской картой\")]")
        ));
        Assertions.assertTrue(payCardTitle.isDisplayed(),
                "Блок оплаты банковской картой не отображается");

    }

    //Заполнение формы и проверка кнопки «Продолжить» для варианта «Услуги связи» (номер 297777777)

    @Test
    public void testFormFillAndContinueButton() throws InterruptedException {
        scrollToPayment();
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
        ));

        // Заполняем номер телефона тестовым номером 297777777
        WebElement phoneInput = block.findElement(By.xpath("//input[@id=\"connection-phone\"]"));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement sumInput = block.findElement(By.xpath("//input[@id=\"connection-sum\"]"));
        sumInput.clear();
        sumInput.sendKeys("20");

        // Проверяем, что кнопка «Продолжить» активна и кликабельна
        WebElement continueButton = block.findElement(By.xpath(".//button[contains(text(), 'Продолжить')]"));

        Assertions.assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' недоступна");

        continueButton.click();
        WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class=\"bepaid-iframe\"]")));
        driver.switchTo().frame(frameElement);
        WebElement cardPage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class=\"card-page__card\"]")
        ));
        Assertions.assertTrue(cardPage.isDisplayed(), "Окошко оплаты не появилось");

    }

    private void scrollToPayment() {

        WebElement element = driver.findElement(By.xpath("//div[@class=\"pay__partners\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}