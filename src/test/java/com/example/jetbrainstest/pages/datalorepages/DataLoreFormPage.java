package com.example.jetbrainstest.pages.datalorepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//https://www.jetbrains.com/datalore/get-in-touch/
public class DataLoreFormPage {
WebDriver driver;

@FindBy(css = ".wt-col-6 button")
private WebElement submitButton;

@FindBy(css = "._errorMessage_1uv984h_4, ._errorMessage_10bo8mm_596")
private List<WebElement> errorMessages;

    public List<String> checkMessagesAfterClick() {
        System.out.println("Проверяем доступность кнопки подтверждения отправки формы");
        assertTrue(submitButton.isEnabled(), "Кнопка не активна");

        System.out.println("Клик по кнопке подтвержения отправки формы");
        submitButton.click();

        // Создаём список для хранения сообщений
        List<String> messages = new ArrayList<>();

        System.out.println("Получаем кол-во предупреждений");
        int i = errorMessages.size();
        int t  = 3;
        assertEquals(t, i ,"Ожидаемое кол-во предупреждений:" +i + "Полученое кол-во предупреждений:" + t);

        System.out.println("Получаем текст каждого предупреждения");
        for (; i > 0; i--) {
            String text = errorMessages.get(i - 1).getText(); // Получаем текст ошибки
            messages.add(text); // Добавляем текст в список
        }

        // Возвращаем список сообщений
        return messages;
    }


public DataLoreFormPage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
}

}
