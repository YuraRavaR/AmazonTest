package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;


public class Elmir {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy(xpath = "//a[@class='cbtel'][1]")
    WebElement headerCallBack;

    @FindBy(xpath = "//a[text()='Контакты'][1]")
    WebElement contacts;

    @FindBy(xpath = "//a[@href='//service.elmir.ua/'][1]")
    WebElement contact;
    @FindBy(xpath = "//div[@id='subscribe-deny']")
    WebElement popupWindow;

    String menuTemplate = "//a[@class='ml-a' and text()='%s']";

    public Elmir(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        BaseTest.LOG.info("Init page");
    }

    public void closePopupWindow(){
        wait.until(ExpectedConditions.visibilityOf(popupWindow));
        popupWindow.click();
        BaseTest.LOG.info("click close Popup Window");
    }

    public void contactsBtnClick() {
        wait.until(ExpectedConditions.visibilityOf(contacts));
        contacts.click();
        BaseTest.LOG.info("Click Contact");
    }

    public void dropDownContactClick() {
        wait.until(ExpectedConditions.visibilityOf(contact));
        contact.click();
        BaseTest.LOG.info("Click Dropdown");
    }

    public void headerCallBackClick() {
        BaseTest.LOG.debug("Try to click on button");
        try{
            headerCallBack.click();
        }catch (Exception e){
            BaseTest.LOG.error("Error click CallBackClick");
        }
        BaseTest.LOG.info("Click Callback");
    }

    public WebElement getMenuByName(String menuName){
        return driver.findElement(By.xpath(String.format(menuTemplate,menuName)));
    }

    public void clickMenuByName(String menuName){
        getMenuByName(menuName).click();
    }

/*    public void hoverMouse() {
        callBack.click();
    }*/
}
