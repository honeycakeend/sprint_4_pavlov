package com.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends Base{

    public OrderPage (WebDriver driver) {
        super(driver);
    }

    //Имя
    public final By name = By.xpath(".//input[@placeholder='* Имя']");

    //Фамилия
    public final By secondName = By.xpath(".//input[@placeholder='* Фамилия']");

    //Адрес
    public final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //метро
    public final By metro = By.xpath(".//input[@placeholder='* Станция метро']");

    //телефон
    public final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка Далее
    public final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //когда привезти
    public final By whereDeliverOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //срок аренды
    public final By rentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");

    //чёрный цвет самоката
    public final By colorBlack = By.id("black");

    //комментарий для курьера
    public final By commentOrder = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка подтверждения заказа
    public final By buttonAgreeOrder =
            By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //кнопка Да на экране подтверждения заказа
    public final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //форма успешного оформления
    public final By orderAgree = By.className("Order_ModalHeader__3FDaJ");

    public void clickButtonYes (){
        driver.findElement(buttonYes).click();
    }

    public void clickOrderAgree(){
        driver.findElement(buttonAgreeOrder).click();
    }

}
