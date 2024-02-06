package com.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Base{

    public MainPage (WebDriver driver) {
        super(driver);
    }

    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Заказать (шапка)
    private final By buttonOrderHeader = By.xpath("//div[contains(@class, 'Header_Nav')]//button[text()='Заказать']");

    //Заказать (боди)
    private final By buttonOrderBody = By.xpath("//div[contains(@class, 'Home_RoadMap')]//button[text()='Заказать']");

    //FAQ блок
    private final By faqSection = By.className("accordion");

    //клик по Заказать (шапка)
    public void clickButtonOrderHeader(){
        driver.findElement(buttonOrderHeader).click();
    }

    //клик по Заказать (боди)
    public void clickButtonOrderBody(){
        WebElement element = driver.findElement(buttonOrderBody);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonOrderBody).click();
    }

    //скролл к faq
    public void scrollToFaq(){
        WebElement element = driver.findElement(faqSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //клик по вопросу в FAQ по номеру вопроса
    public void clickQuestFaq(int numberQuest){
        driver.findElement(By.id("accordion__heading-" + numberQuest)).click();
    }

    //получение актуального вопроса FAQ по номеру вопроса
    public String actualQuestion(int numberQuest){
        return driver.findElement(By.id("accordion__heading-" + numberQuest)).getText();
    }

    //получение актуального ответа FAQ по номеру вопроса
    public String actualAnswer(int numberQuest){
        return driver.findElement(By.id("accordion__panel-" + numberQuest)).getText();
    }
}
