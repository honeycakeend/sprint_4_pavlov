package com.scooter;

import com.scooter.pageobject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    protected WebDriver driver;

    //инициализация драйвера
    @Before
    public void setUp(){
        driver = getDriver();
        driver.get(MainPage.URL);

        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
    }

    //куки
    private void setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
    }

    //закрыть драйвер
    @After
    public void tearDown(){
        driver.quit();
    }

    //настройка браузера для теста
    private WebDriver getDriver(){
        String driverType = "ff"; //для firefox - "ff", для chrome = "chrome"
        switch (driverType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "ff":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Пока не поддерживаются другие браузеры, кроме Chrome и FF");
        }
    }
}
