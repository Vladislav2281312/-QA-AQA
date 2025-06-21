package pages;

import enums.CommunicationServices;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriverWait wait;
    private WebDriver driver;
    final private String startOfFormLocator = "//form[contains(@class,\"pay-form opened\")]";

    // Конструктор
    public MainPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Кнопка "Продолжить"
    @FindBy(xpath = startOfFormLocator + "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    // Элемент для принятия cookies
    @FindBy(xpath = "//button[text()=\"Принять\"]")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = startOfFormLocator + "//input[contains(@id,\"sum\")]")
    private WebElement sumInput;

    // Ввод номера телефона
    @FindBy(xpath = startOfFormLocator + "//input[1]")
    private WebElement infoInput;

    // Ввод номера телефона
    @FindBy(xpath = startOfFormLocator + "//input[contains(@id,\"email\")]")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@class=\"select__header\"]")
    private WebElement serviceConnectionButton;


    // Метод для закрытия окна cookies
    public void acceptCookies() {
        try {
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (Exception e) {
            // Окно не появилось — ничего не делаем
        }
    }

    // Метод для прокрутки до блока "Онлайн пополнение без комиссии"
    public void scrollToPaymentBlock() {
        WebElement element = driver.findElement(By.xpath(".//button[contains(text(), 'Продолжить')]"));
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(element).perform();
    }

    // Метод для получения блока "Онлайн пополнение без комиссии"
    public WebElement getPaymentSection() {
        return driver.findElement(By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]"));
    }

    // Метод для получения заголовка блока
    public WebElement getBlockTitle() {
        return driver.findElement(By.xpath("//h2[contains(text(),\"Онлайн пополнение\")]"));
    }

    // Метод для получения ссылки "Подробнее о сервисе"
    public WebElement getMoreDetailsLink() {
        return driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
    }

    public void fillInfoData(String phone) {
        infoInput.clear();
        infoInput.sendKeys(phone);
    }

    public void fillSum(String sum) {
        sumInput.clear();
        sumInput.sendKeys(sum);
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.visibilityOf(continueButton)).click();
    }

    public void selectCommunicationServices(CommunicationServices option) {
        wait.until(ExpectedConditions.elementToBeClickable(serviceConnectionButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//p[contains(text(),\"%s\")]", option.getTitle())))).click();
    }

    public String getSumPlaceholder() {
        return sumInput.getAttribute("placeholder");
    }

    @Step("Получение значения placeholder для поля Сумма")
    public String getNumberPlaceholder() {
        return infoInput.getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return emailInput.getAttribute("placeholder");
    }


}
