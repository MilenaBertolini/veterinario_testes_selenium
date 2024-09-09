package com.cod3r.gerenciadorfuncionarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VeterinarioTest {
    
    private WebDriver driver;
    
    @BeforeEach
    public void configurar(){
        // WebDriverManager.chromedriver().setup();
        // WebDriverManager.chromedriver().browserVersion("128.0.6613.114").setup();
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    // Milena
    @Test
    public void testarCadastarVeterinarioComSucesso(){

        // verificar se a página correta foi aberta
        driver.get("http://localhost:8080/home");
        assertEquals("Gerenciador de Veterinários", driver.getTitle());
        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/h1"));
                                                                
        assertEquals("Veterinarios", title.getText());

        // buscar pelo botão cadastrar
        WebElement btnCadastrar = driver.findElement(By.xpath("/html/body/div[2]/a[1]/button"));
        btnCadastrar.click();
        
        // buscar por elementos da página cadastrar
        WebElement formNome = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[1]/input"));
        WebElement formEmail = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[2]/input"));
        WebElement formEspecialidade = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[3]/input"));
        WebElement formSalario = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[4]/input"));
        WebElement btnFormCadastrar = driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/button"));
        

        // manipular os elementos do forms
        formNome.sendKeys("Daiane Ferreira de Oliveira");
        formEmail.sendKeys("daianeferreira@mail.com");
        formEspecialidade.sendKeys("Animais exóticos");
        formSalario.sendKeys("5000");
        btnFormCadastrar.click();

        // buscar pelo novo cadastro
        WebElement novaVetNome = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[1]/span"));
        WebElement novaVetEspecialidade = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[2]/span"));
        WebElement novaVetEmail = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[3]/span"));
        WebElement novaVetSalario = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[4]/span"));

        // verificar se realmente cadastrou
        assertEquals("Daiane Ferreira de Oliveira", novaVetNome.getText());
        assertEquals("Animais exóticos", novaVetEspecialidade.getText());
        assertEquals("daianeferreira@mail.com", novaVetEmail.getText());
        assertEquals("5000.00", novaVetSalario.getText());

        driver.quit();
    }
    
}
