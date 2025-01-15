package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.datalorepages.DataLorePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataLoreTest extends BaseTest {
    private DataLorePage dataLorePage;

    @BeforeEach
    @Override
    @Step("Переход на страницу DataLore")
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/datalore/");
        dataLorePage = new DataLorePage(getDriver());
    }

    @Test
    @DisplayName("Проверка доступности кнопки-заголовка")
    public void checkPageIsAvailable() {
        assertTrue(dataLorePage.checkTitle(), "Кнопка доступна");
    }

    @Test
    @DisplayName("Проверка количества элементов в свитчере")
    public void switcherCheck(){
//        dataLorePage.switcherHasFourElements();
        int exp = 4;
        assertEquals(exp, dataLorePage.switcherHasFourElements(), "Свитчер содержит 4 элемента");
    }
}
