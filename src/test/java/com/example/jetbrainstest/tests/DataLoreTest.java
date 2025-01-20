package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.datalorepages.DataLoreFormPage;
import com.example.jetbrainstest.pages.datalorepages.DataLorePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataLoreTest extends BaseTest {
    private final Logger log = LoggerFactory.getLogger(DataLoreTest.class);
    private DataLorePage dataLorePage;
    private DataLoreFormPage dataLoreFormPage;

    @BeforeEach
    @Override
    @Step("Переход на страницу DataLore")
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/datalore/");
        dataLorePage = new DataLorePage(getDriver());
        dataLoreFormPage = new DataLoreFormPage(getDriver());
    }

    @Test
    @DisplayName("Проверка доступности кнопки-заголовка")
    public void checkPageIsAvailable() {
        assertTrue(dataLorePage.checkAndGetTitle(), "Кнопка не доступна");
    }

    @Test
    @DisplayName("Проверка количества элементов в свитчере")
    public void numberElementsOfSwitcherCheck() {
        int exp = 4;
        assertEquals(exp, dataLorePage.GetCountElementsInSwitcher(), "Свитчер не содержит 4 элемента");
    }

    @Test
    @DisplayName("Появление предупреждений о бязательном заполнении полей после клика по кнопке")
    public void errorMessagesInGetBackForm() {
        dataLorePage.clickDemoButton();
        List<String> actualMessages = dataLoreFormPage.checkAndGetErrorMessagesAfterClick();
        List<String> expectedMessages = Collections.nCopies(3, "This field is required");
        log.info("Проверяем текст предупреждений");
        assertEquals(expectedMessages, actualMessages, "Тексты предупреждений не совпадают");
    }
}
