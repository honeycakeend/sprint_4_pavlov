package com.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends Base{

    public OrderPage (WebDriver driver) {
        super(driver);
    }
    public static final String URL_ORDER = "https://qa-scooter.praktikum-services.ru/order";

    //Имя
    private final By name = By.xpath(".//input[@placeholder='* Имя']");

    //Фамилия
    private final By secondName = By.xpath(".//input[@placeholder='* Фамилия']");

    //Адрес
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //метро
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");

    //телефон
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка Далее
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //когда привезти
    private final By whereDeliverOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //срок аренды
    private final By rentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");

    //чёрный цвет самоката
    private final By colorBlack = By.id("black");

    //комментарий для курьера
    private final By commentOrder = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка подтверждения заказа
    private final By buttonAgreeOrder =
            By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //кнопка Да на экране подтверждения заказа
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //форма успешного оформления
    private final By orderAgree = By.className("Order_ModalHeader__3FDaJ");

    public void clickButtonYes (){
        driver.findElement(buttonYes).click();
    }

    public void clickOrderAgree(){
        driver.findElement(buttonAgreeOrder).click();
    }

    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

    //заполнение данных юзера в форме
    public void userDataEntry(String name, String secondName, String adress, int stantionMetro, String phone){
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.secondName).sendKeys(secondName);
        driver.findElement(this.address).sendKeys(adress);
        driver.findElement(this.metro).click();
        driver.findElement(By.xpath(".//ul[@class='select-search__options']/li[@data-value = '" + stantionMetro +"']")).click();
        driver.findElement(this.phone).sendKeys(phone);
    }

    //заполнение данных по самокату
    public void scooterDataEntry(String userWhereDeliverOrder, String userRentalPeriod, String userComment){
        driver.findElement(this.whereDeliverOrder).sendKeys(userWhereDeliverOrder);
        driver.findElement(this.rentalPeriod).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option' and text() = '" + userRentalPeriod + "']")).click();
        driver.findElement(this.colorBlack).click();
        driver.findElement(this.commentOrder).sendKeys(userComment);
    }

    //текст формы успешного оформления
    public String textAgreeForm(){
        return driver.findElement(orderAgree).getText();
    }
}
