package tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.Amazon;

import java.util.Map;

public class AdidasTest extends BaseTest {

    //protected Amazon storePage;

    /*    @BeforeSuite
    public void setupChromeDriver() {
        WebDriverManager.chromedriver()
                .browserVersion("93.0.4577.63")
                .setup();
        options = new ChromeOptions();
        options.addArguments("start-maximized");
    }*/

    @Test
    public void testCase1() {
        driver.get("https://amazon.com/");
        Amazon storePage = PageFactory.initElements(driver, Amazon.class);

        storePage.searchProduct("adidas shoes men");
        storePage.setSneakersType();
        storePage.setMinPriceFilter("40");
        storePage.goSearch();

        Map<String, String> mapOfDistinctSneakers = storePage.getAllDistinctProducts();
        SoftAssert softAssert = new SoftAssert();
        mapOfDistinctSneakers.forEach((key, value) ->
                softAssert.assertTrue(value != "$0.00", ("Product " + key + " has price $0.00")));
        softAssert.assertAll();
    }

}