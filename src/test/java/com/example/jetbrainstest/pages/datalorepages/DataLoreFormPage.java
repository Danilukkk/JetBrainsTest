package com.example.jetbrainstest.pages.datalorepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://www.jetbrains.com/datalore/get-in-touch/
public class DataLoreFormPage {
    WebDriver driver;
    private final Logger log = LoggerFactory.getLogger(DataLoreFormPage.class);

    @FindBy(css = ".wt-col-6 button")
    private WebElement submitButton;

    @FindBy(css = "._errorMessage_1uv984h_4, ._errorMessage_10bo8mm_596")
    private List<WebElement> errorMessages;

    public List<String> checkAndGetErrorMessagesAfterClick() {
        log.info("Проверяем доступность кнопки подтверждения отправки формы");
        assertTrue(submitButton.isEnabled(), "Кнопка не активна");

        log.info("Клик по кнопке подтвержения отправки формы");
        submitButton.click();

        List<String> messages = new ArrayList<>();
        log.info("Получаем кол-во предупреждений");
        int i = errorMessages.size();
        int t = 3;
        assertEquals(t, i, "Ожидаемое кол-во предупреждений: " + i + " Полученое кол-во предупреждений: " + t);

        log.info("Получаем текст каждого предупреждения");
        for (; i > 0; i--) {
            String text = errorMessages.get(i - 1).getText();
            messages.add(text);
        }
        return messages;
    }

    public DataLoreFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
