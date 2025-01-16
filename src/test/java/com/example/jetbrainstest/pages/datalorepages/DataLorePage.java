package com.example.jetbrainstest.pages.datalorepages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
//URL страницы https://www.jetbrains.com/datalore/

public class DataLorePage {
    WebDriver driver;

    @FindBy(css = ".menu-second-title-box__title")
    private WebElement pageTitle;

    @FindBy(css = ".toc__switcher")
    private WebElement switcher;

    @FindBy(xpath = "//span[contains(@class,  'toc__switcher')]/button")
    private List<WebElement> pieceSwitcher;

    @FindBy (xpath = "//div[@class = 'datalore-registration-form']/a")
    private WebElement getADemoButton;

    public boolean checkTitle() {
        System.out.println("Проверка активности кнокпи-заголовка");
        return pageTitle.isEnabled();
    }
//
    public int switcherHasFourElements() {
        System.out.println("Проверка наличия элемента на странице");
        assertTrue(switcher.isEnabled(), "Свитчер доступен");
        System.out.println("Получение элементов свитчера");
        int count = pieceSwitcher.size();
        System.out.println("Кол-во элементов в свитчере: " + count);
        return count;
    }
    public void clickDemoButton() {
        System.out.println("Проверка наличия кнопки Demo на странице");
        assertTrue(getADemoButton.isEnabled(), "Кнопка не доступна");
        System.out.println("Клик по кнопке для перехода на страницу обратной связи");
        getADemoButton.click();
    }

    public DataLorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
