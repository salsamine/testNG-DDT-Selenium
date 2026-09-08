package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExcelReader;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // TODO: Create WebDriver
        // TODO: Open https://www.saucedemo.com/
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    private void login (String username, String password){
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
    @Test
    public void hardAssertLoginTest() {
        // TODO:
        // 1. Perform a valid login
        // 2. Add at least one Hard Assert
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void softAssertLoginTest() {
        // TODO:
        // 1. Perform a valid login
        // 2. Add at least two Soft Assert validations
        // 3. Remember to call assertAll()
        login("standard_user", "secret_sauce");
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html","Login gagal karena username/password salah");
        softAssert.assertTrue(driver.findElement(By.className("product_sort_container")).isDisplayed(),"product sort container isn't displayed");
        softAssert.assertTrue(driver.findElement(By.className("app_logo")).isDisplayed(), "app logo isn't displayed");
        softAssert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(), "burger menu button isn't displayed");
        softAssert.assertTrue(driver.findElement(By.className("shopping_cart_link")).isDisplayed(), "shopping cart icon isn't displayed");
        softAssert.assertTrue(driver.findElement(By.className("title")).isDisplayed(), "product title isn't displayed");
        softAssert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed(), "inventory container isn't displayed");
        softAssert.assertAll();
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        // TODO: Read login-data.xlsx using Apache POI
        // Return: testCase, username, password, expectedResult
        return ExcelReader.readExcel("src/test/resources/testdata/login-data.xlsx" );
    }

    @Test(dataProvider = "loginData")
    public void dataDrivenLoginTest(
            String testCase,
            String username,
            String password,
            String expectedResult) {
        // TODO:
        // 1. Login using username/password
        // 2. Validate result based on expectedResult
        login(username, password);
        switch (expectedResult) {
            case "SUCCESS":
                Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",testCase + " is success");
                break;
            case "INVALID_USERNAME":
                Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed(), testCase + " : error message is  displayed");
                break;
            case "INVALID_PASSWORD":
                Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed(), testCase + " : error message is  displayed");
                break;
                default:
                    Assert.fail("Invalid Expected : " + expectedResult);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver !=null) {
            driver.quit();
        }
        // TODO: Quit browser
    }
}
