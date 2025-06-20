package views;

import enums.CommunicationServices;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentView {
    private WebDriverWait wait;
    private WebDriver driver;

    public PaymentView(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Ифрейм оплаты
    @FindBy(xpath = "//iframe[@class=\"bepaid-iframe\"]")
    private WebElement paymentIframe;

    // Блок оплаты внутри iframe
    @FindBy(xpath = "//div[@class=\"card-page__card\"]")
    private WebElement paymentForm;

    public void switchToPaymentFrame() {
        driver.switchTo().frame(paymentIframe);
    }

    public boolean isPaymentFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(paymentForm)).isDisplayed();
    }

    public WebElement getPaymentIframe() {
        return paymentIframe;
    }
}