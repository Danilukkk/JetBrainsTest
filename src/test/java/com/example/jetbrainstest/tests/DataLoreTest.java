package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.AllureLogger;
import com.example.jetbrainstest.MyExtension;
import com.example.jetbrainstest.pages.datalorepages.DataConnectionsPage;
import com.example.jetbrainstest.pages.datalorepages.DataLoreFormPage;
import com.example.jetbrainstest.pages.datalorepages.DataLorePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class DataLoreTest extends BaseTest {
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(DataLoreTest.class));
    private DataLorePage dataLorePage;
    private DataLoreFormPage dataLoreFormPage;
    private DataConnectionsPage dataConnectionsPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        log.info("Переход на страницу DataLore");
        getDriver().get("https://www.jetbrains.com/datalore/");
        dataLorePage = new DataLorePage(getDriver());
        dataLoreFormPage = new DataLoreFormPage(getDriver());
        dataConnectionsPage = new DataConnectionsPage(getDriver());
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
        assertEquals(exp, dataLorePage.getCountElementsInSwitcher(), "Свитчер не содержит 4 элемента");
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

    @Test
    @DisplayName("Редирект на страницу Data connections после клика по кнопке Explore all supported data integrations")
    public void redirectToDataConnectionsAfterClickExploreDataButton() {
        dataLorePage.checkAndClickExploreDataButton();
        assertTrue(dataConnectionsPage.checkDataTitle(), "Заголовок не отобразился");
    }

    @Test
    @DisplayName("Отображение видео в разделе Data")
    public void checkVideoInDataChapter() {
        assertTrue(dataLorePage.checkVideo(), "Плеер отсутствует");
    }

    @Test
    @DisplayName("Наличие на странице модуля Cloud Free")
    public void cloudFreeCheck() {
        assertTrue(dataLorePage.cloudFreeVisible(), "Модуль отсутствует");
    }

    @Test
    @DisplayName("Раскрытие дроп даун меню по клику в модуле Cloud Free")
    public void dropDownCloudFreeIsOpenAfterClick() {
        assertTrue(dataLorePage.checkAndClickGetStarted(), "Меню не раскрылось");
    }

    @Test
    @DisplayName("Ввод валидного email в форму подписки на новости в подвале сайта")
    public void addValidEmailInFooterNewsForm() {
        dataLorePage.checkAndSendMail("t@gmail.com");
        assertTrue(dataLorePage.checkStatementOfSubmitButton(), "Кнопка не исчезла");
    }

    @Test
    @DisplayName("Ввод невалидного email в форму подписки на новости в подвале сайта")
    public void addInvalidEmailInFooterNewsForm() {
        dataLorePage.checkAndSendMail("asdasd");
        assertFalse(dataLorePage.checkStatementOfSubmitButton(), "Кнопка исчезла");
    }

    @Test
    @DisplayName("Оставить поле email пустым в форме подписки на новости в подвале сайта")
    public void addEmptyEmailInFooterNewsForm() {
        dataLorePage.checkAndSendMail("");
        assertFalse(dataLorePage.checkStatementOfSubmitButton(), "Кнопка исчезла");
    }

    @Test
    @DisplayName("Проверка сообщения после ввода валидного email в форму подписки на новости в подвале сайта")
    public void addValidEmailInFooterNewsFormAndCheckMessage() {
        dataLorePage.checkAndSendMail("test@gmail.com");
        assertTrue(dataLorePage.checkTextAfterSubmitValidEmail("Thanks for your request!"), "Текст не совпадает");
    }

    @Test
    @DisplayName("Проверка сообщения после ввода невалидного email в форму подписки на новости в подвале сайта")
    public void addInvalidEmailInFooterNewsFormAndCheckMessage() {
        dataLorePage.checkAndSendMail("testgmail.com");
        assertTrue(dataLorePage.checkTextAfterSubmitInvalidEmail("E-mail address is not correct"), "Текст не совпадает");
    }

    @Test
    @DisplayName("Проверка сообщения после оставления поля email пустым в форме подписки на новости в подвале сайта")
    public void addEmptyEmailInFooterNewsFormAndCheckMessage() {
        dataLorePage.checkAndSendMail("");
        assertTrue(dataLorePage.checkTextAfterSubmitEmptyEmail("This field is required"), "Текст не совпадает");
    }

    @Test
    @DisplayName("Наличие блока Cloud на странице")
    public void checkCloudOnPage() {
        assertTrue(dataLorePage.checkModuleCloud(), "Модуль отсутствует");
    }

    @Test
    @DisplayName("Количество элементов в свитчере в блоке Cloud")
    public void checkNumberOfElementsInCloudSwitcher() {
        int exp = 2;
        assertEquals(exp, dataLorePage.checkNumberOfElementsInSwitcherCloud(), "Кол-во элементов не совпадает");
    }

    @Test
    @DisplayName("Начальное положение положение свитчера в блоке Cloud")
    public void startPositionOfSwitcher() {
        assertTrue(dataLorePage.checkSwitcherPosition(), "Начальное положение не совпадает");
    }

    @Test
    @DisplayName("Переключение свитчера в блоке CLoud на Monthly billing")
    public void switchToMonthlyBilling() {
        dataLorePage.checkSwitcherPositionAndClickOnMonthly();
        assertTrue(dataLorePage.switcherHasSwitched(), "Свитчер не переключился");
    }

    @Test
    @DisplayName("Стоимость подписки Yearly billing в блоке CLoud")
    public void yearlyBillingCost() {
        assertTrue(dataLorePage.getAndCheckYearlyPrice().startsWith("US $29.17"), "Цена не совпадает");
    }

    @Test
    @DisplayName("Стоимость подписки Monthly billing в блоке CLoud")
    public void monthlyBillingCost() {
        dataLorePage.checkSwitcherPositionAndClickOnMonthly();
        dataLorePage.switcherHasSwitched();
        String ePrice = "US $35.00";
        assertEquals(ePrice, dataLorePage.getAndCheckMonthlyPrice(), "Цена не совпадает");
    }

    @Test
    @DisplayName("Появляется всплывающее окно с выбором региона по клику кнопки выбора региона в футере страницы")
    public void regionButtonCheck() {
        dataLorePage.checkAndClickRegionButton();
        assertTrue(dataLorePage.popUpRegionCheck(), "Окно не отобразилось");
    }

    @Test
    @DisplayName("Выбор региона Австралия в всплывающем окне выбора региона по клику кнопки выбора региона в футере страницы")
    public void chooseAustraliaRegion() {
        dataLorePage.checkAndClickRegionButton();
        assertTrue(dataLorePage.popUpRegionCheck(), "Окно не отобразилось");
        dataLorePage.clickDropDownMenuAndChooseRegionAustralia();
        String expRegion = "Australia";
        assertEquals(expRegion, dataLorePage.checkChosenRegion(), "Регион не совпадает");
    }

}

