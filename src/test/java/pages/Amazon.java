package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BaseTest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Amazon {

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(xpath = "//a[normalize-space()='Sneakers']")
    WebElement sneakersFilterButton;

    @FindBy(id = "low-price")
    WebElement minPriceFilter;

    @FindBy(id = "a-autoid-1")
    WebElement goButton;

    //@FindBy(xpath = "//*[contains(@data-component-type,'s-search-result')]")
    @FindBy(xpath = "//*[contains(@data-component-type,'s-search-result')]//div[@data-component-type='s-search-result']")
    List<WebElement> allSneakers;

    public Map<String, String> getAllDistinctProducts() {
        Map<String, String> result = new LinkedHashMap<>();
        for (WebElement el : allSneakers) {
            //String name = el.findElement(By.cssSelector(".a-size-base-plus.a-color-base.a-text-normal")).getText();
            String name = null;
            try {
                name = el.findElement(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']")).getText();
            } catch (Exception ex) {
                System.out.println("Error: "+result);
            }
            try {
                //String price = el.findElement(By.cssSelector(".a-row.a-size-base.a-color-base span span")).getText();
                String price = el.findElement(By.xpath(".//span[@class='a-price']//span[@class='a-offscreen']")).getAttribute("outerText");
                result.put(name, price);
            } catch (Exception ex) {
                result.put(name, "$0.00");
            }
        }
        BaseTest.LOG.info("Found " + result.size() + " products");
        return result;
    }

    public void searchProduct(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
            BaseTest.LOG.info("search product: " + text);
    }

    public void setMinPriceFilter(String text) {
        minPriceFilter.clear();
        minPriceFilter.sendKeys(text);
            BaseTest.LOG.info("Set min price of filter: " +  text);

    }

    public void setSneakersType() {
        sneakersFilterButton.click();
            BaseTest.LOG.info("Click button type Sneakers");
    }

    public void goSearch() {
        goButton.click();
            BaseTest.LOG.info("Click GO button in the filter");
    }
}
