package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.AllureLogger;
import com.example.jetbrainstest.MyExtension;
import com.example.jetbrainstest.pages.datalorepages.DataLoreFormPage;
import com.example.jetbrainstest.pages.datalorepages.DataLorePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyExtension.class)
public class DataLoreTest extends BaseTest {
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(DataLoreTest.class));
    private DataLorePage dataLorePage;
    private DataLoreFormPage dataLoreFormPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        log.info("Переход на страницу DataLore");
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
