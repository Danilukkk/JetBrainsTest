package com.example.jetbrainstest.pages.datalorepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
//URL страницы https://www.jetbrains.com/datalore/

public class DataLorePage {
    WebDriver driver;
    private final Logger log = LoggerFactory.getLogger(DataLorePage.class);

    @FindBy(css = ".menu-second-title-box__title")
    private WebElement pageTitle;

    @FindBy(css = ".toc__switcher")
    private WebElement switcher;

    @FindBy(xpath = "//span[contains(@class,  'toc__switcher')]/button")
    private List<WebElement> pieceSwitcher;

    @FindBy(xpath = "//div[@class = 'datalore-registration-form']/a")
    private WebElement getADemoButton;

    public boolean checkTitle() {
        log.info("Проверка активности кнокпи-заголовка");
        return pageTitle.isEnabled();
    }

    public int switcherHasFourElements() {
        log.info("Проверка наличия элемента на странице");
        assertTrue(switcher.isEnabled(), "Свитчер доступен");
        log.info("Получение элементов свитчера");
        int count = pieceSwitcher.size();
        System.out.println( + count);
        log.info("Кол-во элементов в свитчере: {}",count);
        return count;
    }

    public void clickDemoButton() {
        log.info("Проверка наличия кнопки Demo на странице");
        assertTrue(getADemoButton.isEnabled(), "Кнопка не доступна");
        log.info("Клик по кнопке для перехода на страницу обратной связи");
        getADemoButton.click();
    }

    public DataLorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
