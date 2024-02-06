package com.scooter;

import com.scooter.pageobject.MainPage;
import com.scooter.pageobject.OrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest{

    private final String userName;
    private final String userSecondName;
    private final String userAdress;
    private final int stantionMetro;
    private final String userPhone;
    private final String userWhereDeliverOrder;
    private final String userRentalPeriod;
    private final String userComment;

    public OrderPageTest(String userName, String userSecondName, String userAdress, int stantionMetro, String userPhone,
                         String userWhereDeliverOrder, String userRentalPeriod, String userComment) {
        this.userName = userName;
        this.userSecondName = userSecondName;
        this.userAdress = userAdress;
        this.userPhone = userPhone;
        this.stantionMetro = stantionMetro;
        this.userWhereDeliverOrder = userWhereDeliverOrder;
        this.userRentalPeriod = userRentalPeriod;
        this.userComment = userComment;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][]{
                { "Инокентий", "Смактуновский", "Какой-то город и улица", 1, "+70000000001", "24.02.2024",
                        "сутки", "ты говорил, что шаришь в этой теме"},
                { "Екатерина", "Катериновна", "Какое-то сели и улица", 2, "+70000000001", "26.02.2024",
                        "двое суток", "а ну утро из меня вышла черепаха"}
        };
    }

    //тест положительного сценария заказа
    @Test
    public void testPositiveOrder(){

        //переход на страницу заказа
        driver.get(MainPage.URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonOrderHeader();

        OrderPage orderPage = new OrderPage(driver);

        //заполнение данных юзера
        orderPage.userDataEntry(userName, userSecondName, userAdress, stantionMetro, userPhone);
        orderPage.clickButtonNext();

        //заполнение данных по самокату
        orderPage.scooterDataEntry(userWhereDeliverOrder, userRentalPeriod, userComment);

        //подтверждение заказа и проверка оформления
        orderPage.clickOrderAgree();
        orderPage.clickButtonYes();
        Assert.assertTrue(orderPage.textAgreeForm().contains("Заказ оформлен"));
    }

    //проверка перехода на страницу заказа с кнопки в боди
    @Test
    public void testNavigatingThroughButtons(){
        driver.get(MainPage.URL);
        MainPage mainPage = new MainPage(driver);

        mainPage.clickButtonOrderBody();
        Assert.assertEquals(OrderPage.URL_ORDER, driver.getCurrentUrl());
    }
}
