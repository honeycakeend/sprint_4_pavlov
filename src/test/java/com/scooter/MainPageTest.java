package com.scooter;

import com.scooter.pageobject.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MainPageTest extends BaseTest{

    //кол-во вопросов в faq
    private int numberQuest = 8;

    //ожидаемые вопросы
    String[] expectedQuests = {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я жизу за МКАДом, привезёте?"
    };

    //ожидаемые ответы
    String[] expectedAnswer = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто" +
                    " сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени" +
                    " аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат" +
                    " 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься" +
                    " без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    //тест на проверку faq (вопросы и ответы)
    @Test
    public void checkTextFaq(){

        driver.get(MainPage.URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToFaq();

        String[] questActual = new String[numberQuest];
        String[] answerActual = new String[numberQuest];

        for (int i = 0; i < questActual.length; i++) {
            driver.findElement(By.id("accordion__heading-" + i)).click();
            questActual[i] = driver.findElement(By.id("accordion__heading-" + i)).getText();
            answerActual[i] = driver.findElement(By.id("accordion__panel-" + i)).getText();
        }

        Assert.assertArrayEquals(questActual, expectedQuests);
        Assert.assertArrayEquals(answerActual, expectedAnswer);
    }

    //тест переход с главной на страницу заказа через кнопки Заказать в шапке и в боди
    @Test
    public void testNavigatingThroughButtons(){
        //проверка перехода на страницу заказа с кнопки в шапке
        driver.get(MainPage.URL);
        MainPage mainPage = new MainPage(driver);

        mainPage.clickButtonOrderHeader();
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/order", driver.getCurrentUrl());

        //проверка перехода на страницу заказа с кнопки в боди
        driver.navigate().back();
        mainPage.clickButtonOrderBody();
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/order", driver.getCurrentUrl());
    }

}
