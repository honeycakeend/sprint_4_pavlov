package com.scooter;

import com.scooter.pageobject.MainPage;
import com.scooter.pageobject.OrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

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


        //заполнение данных юзера
        OrderPage orderPage = new OrderPage(driver);
        driver.findElement(orderPage.name).sendKeys(userName);
        driver.findElement(orderPage.secondName).sendKeys(userSecondName);
        driver.findElement(orderPage.address).sendKeys(userAdress);
        driver.findElement(orderPage.metro).click();
        driver.findElement(By.xpath(".//ul[@class='select-search__options']/li[@data-value = '" + stantionMetro +"']")).click();
        driver.findElement(orderPage.phone).sendKeys(userPhone);

        driver.findElement(orderPage.buttonNext).click();

        //заполнение данных по самокату
        driver.findElement(orderPage.whereDeliverOrder).sendKeys(userWhereDeliverOrder);
        driver.findElement(orderPage.rentalPeriod).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option' and text() = '" + userRentalPeriod + "']")).click();
        driver.findElement(orderPage.colorBlack).click();
        driver.findElement(orderPage.commentOrder).sendKeys(userComment);


        //подтверждение заказа и проверка оформления
        orderPage.clickOrderAgree();
        orderPage.clickButtonYes();
        String agreeText = driver.findElement(orderPage.orderAgree).getText();
        Assert.assertTrue(agreeText.contains("Заказ оформлен"));
    }
}
