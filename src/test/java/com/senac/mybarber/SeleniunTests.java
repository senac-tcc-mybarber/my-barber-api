package com.senac.mybarber;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * SeleniunTests
 */
public class SeleniunTests {

    private String testUrl;
    private WebDriver driver;

    @Before
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");

        driver = new ChromeDriver();

        testUrl = "https://rafaelcruz.dev";

        driver.get(testUrl);
    }

    @Test()
    public void testTitle() {
        assertTrue("Exibir titulo de pagina", 
                   (new WebDriverWait(driver, 5))
                   .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return d.getTitle().equals("Rafael Cruz");
                        }
                   }));
    }

    @Test()
    public void testSearch() {
        WebElement searchText = driver.findElement(By.className("search-field"));
        WebElement searchBtn = driver.findElement(By.id("search-submit"));

        searchText.sendKeys("oauth");

        searchBtn.click();

        assertTrue("Exibir resultado de teste", 
        (new WebDriverWait(driver, 5))
        .until(new ExpectedCondition<Boolean>() {
             public Boolean apply(WebDriver d) {
                WebElement result = driver.findElement(By.className("page-title"));
                return result.getText().toUpperCase().contains("OAUTH");

             }
        }));
        
    }

    @After()
    public void tearDown() {
        driver.quit();
    }
    
    
}