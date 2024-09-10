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
    
    //Barbara
    @Test
    public void testarPesquisarVeterinario(){

        // verificar se a página correta foi aberta
        driver.get("http://localhost:8080/home");
        assertEquals("Gerenciador de Veterinários", driver.getTitle());
        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/h1"));
        assertEquals("Veterinarios", title.getText());

        // buscar pelo botão pesquisar
        WebElement btnPesquisar = driver.findElement(By.xpath("/html/body/div[2]/a[2]/button"));
        btnPesquisar.click();
        
        // verificar se está na página de pesquisar
        WebElement titlePesquisar = driver.findElement(By.xpath("/html/body/div[2]/h1"));
        assertEquals("Pesquisar veterinários", titlePesquisar.getText());

        // buscar pelo campo de pesquisa, adicionar nome e pesquisar
        WebElement inputNome = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div/input"));
        inputNome.sendKeys("Erica Queiroz Pinto");
        WebElement btnPesquisa = driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/button"));
        btnPesquisa.click();

        // verificar se a consulta funcionou
        WebElement nomeRetornado = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[1]/span"));
        WebElement especialidadeRetornada = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]/span"));
        WebElement emailRetornado = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[3]/span"));
        WebElement salarioRetornado = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[4]"));

        // verificar se a pesquisa retornada está correta
        assertEquals("Erica Queiroz Pinto", nomeRetornado.getText());
        assertEquals("grandes", especialidadeRetornada.getText());
        assertEquals("erica@gmail.com", emailRetornado.getText());
        assertEquals("R$4500.00", salarioRetornado.getText());

        driver.quit();
    }
    
    @Test
    public void testarAtualizaVeterinario(){

        // verificar se a página correta foi aberta
        driver.get("http://localhost:8080/home");
        assertEquals("Gerenciador de Veterinários", driver.getTitle());
        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/h1"));
                                                                
        assertEquals("Veterinarios", title.getText());

        // buscar pelo botão atualizar a veterinária Daiane Ferreira de Oliveira
        WebElement btnAtualizar = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[5]/a[1]"));
        btnAtualizar.click();
        
        // buscar por elementos da página cadastrar
        WebElement formNome = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[1]/input"));
        WebElement formEmail = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[2]/input"));
        WebElement formEspecialidade = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[3]/input"));
        WebElement formSalario = driver.findElement(By.xpath("/html/body/div[2]/form/div[1]/div[4]/input"));
        
        // verificar se o cliente aberto para a atualização foi o informado
        assertEquals("Daiane Ferreira de Oliveira", formNome.getAttribute("value"));
        assertEquals("daianeferreira@mail.com", formEmail.getAttribute("value"));
        assertEquals("Animais exóticos", formEspecialidade.getAttribute("value"));
        assertEquals("5000.00", formSalario.getAttribute("value"));
        
        // atualizar o salário do veterinário
        formSalario.clear();
        formSalario.sendKeys("5740,60");
        WebElement btnAtualizaDados = driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/button"));
        btnAtualizaDados.click();

        // buscar pelo veterinario atualizado
        WebElement atualizadoVetNome = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[1]/span"));
        WebElement atualizadoVetEspecialidade = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[2]/span"));
        WebElement atualizadoVetEmail = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[3]/span"));
        WebElement atualizadoVetSalario = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[4]/span"));

        // verificar se atualizou o veterinário e somente houve alteração no campo salário
        assertEquals("Daiane Ferreira de Oliveira", atualizadoVetNome.getText());
        assertEquals("Animais exóticos", atualizadoVetEspecialidade.getText());
        assertEquals("daianeferreira@mail.com", atualizadoVetEmail.getText());
        assertEquals("5740.60", atualizadoVetSalario.getText());

        driver.quit();
    }
}
