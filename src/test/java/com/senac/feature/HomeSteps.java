package com.senac.feature;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import cucumber.api.java.pt.Entao;

/**
 * HomeSteps
 */
public class HomeSteps {

    private String testUrl;
    private WebDriver driver;
    WebElement searchText;
    WebElement searchBtn;

    @Dado("que deseja entra no blog")
    public void que_desejo_entrar_no_blog() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Quando("abrir o browser")
    public void abrir_o_browser() {
        testUrl = "https://rafaelcruz.azurewebsites.net";
        driver.get(testUrl);
    }

    @Entao("deverá aparecer o site")
    public void devera_aparecer_o_site() {
        assertTrue("Exibir titulo de pagina", 
        (new WebDriverWait(driver, 5))
        .until(new ExpectedCondition<Boolean>() {
             public Boolean apply(WebDriver d) {
                 return d.getTitle().equals("Rafael Cruz");
             }
        }));

    }

    @E("digitar '(.*?)' no campo de busca")
    public void digitar_no_campo_de_busca(String searchTerm) {
        this.searchText = driver.findElement(By.className("search-field"));
        searchText.sendKeys(searchTerm);
    }

    @E("clicar no botao pesquisar")
    public void e_clicar_no_botao_pesquisar() {
        searchBtn = driver.findElement(By.id("search-submit"));
        searchBtn.click();
    }

    @Entao("deverá aparecer o resultado de busca")
    public void devera_aparecer_o_resultado_de_busca() {
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