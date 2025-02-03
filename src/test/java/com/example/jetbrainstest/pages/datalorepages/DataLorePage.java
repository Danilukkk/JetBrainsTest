package com.example.jetbrainstest.pages.datalorepages;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

import static com.example.jetbrainstest.MyWait.myWait;
import static org.junit.jupiter.api.Assertions.assertTrue;
//URL страницы https://www.jetbrains.com/datalore/

public class DataLorePage {
    WebDriver driver;
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(DataLorePage.class));

    @FindBy(css = ".menu-second-title-box__title")
    private WebElement pageTitle;

    @FindBy(css = ".toc__switcher")
    private WebElement switcher;

    @FindBy(xpath = "//span[contains(@class,  'toc__switcher')]/button")
    private List<WebElement> pieceSwitcher;

    @FindBy(xpath = "//div[@class = 'datalore-registration-form']/a")
    private WebElement getDemoButton;

    @FindBy(xpath = "//div/a[contains(normalize-space(text()), 'Explore all supported data integrations')]")
    private WebElement buttonExplore;

    @FindBy(css = "div video")
    private List<WebElement> videoPlayerInData;

    @FindBy(xpath = "//div/h2[contains(normalize-space(text()), 'Cloud Free')]")
    private WebElement moduleCloudFree;

    @FindBy(xpath = "  //button/span[contains(normalize-space(text()), 'Get Started')]")
    private WebElement buttonGetStarted;

    @FindBy(xpath = "//div//h4")
    private WebElement dropDownHeader;

    @FindBy(css = ".datalore-social-footer input[name = 'email']")
    private WebElement footerMailForm;

    @FindBy(css = ".datalore-social-footer button")
    private WebElement footerSubmitButton;

    @FindBy(css = ".datalore-social-footer p")
    private WebElement textAfterValidMail;

    @FindBy(xpath = "//div[contains(normalize-space(text()), 'E-mail address is not correct')]")
    private WebElement textAfterInvalidMail;

    @FindBy(xpath = "//div[contains(normalize-space(text()), 'This field is required')]")
    private WebElement textAfterEmptyMail;

    @FindBy(xpath = "//div/h2[normalize-space(text()) = 'Cloud']")
    private WebElement moduleCloud;

    @FindBy(css = ".card-template__description .wt-col-inline")
    private List<WebElement> switcherCloudElements;

    @FindBy(css = ".card-template__description [class*='_selected']")
    private List<WebElement> startSwitcherPosition;

    @FindBy(xpath = "//span[@data-test=\"adaptive-switcher__switcher\"]//div[contains(normalize-space(text()), 'Monthly billing')]")
    private WebElement buttonMonthlyBilling;

    @FindBy(xpath = "//span[contains(@style, 'left: 110')]")
    private WebElement buttonMonthlyBillingIsActive;

    @FindBy(xpath = "//div[contains(normalize-space(text()), 'US' )]")
    private WebElement yearlyPrice;

    @FindBy(xpath = "//div[contains(normalize-space(text()), '35.')]")
    private WebElement monthlyPrice;

    @FindBy(xpath = "//div/button[@data-test = 'footer-country-button']")
    private WebElement regionButton;

    @FindBy(xpath = "//div/div[@data-test='popup-header']")
    private WebElement popUpRegion;

    @FindBy(css = ".wt-popup__content input")
    private WebElement regionDropDownMenu;

    @FindBy(xpath = "//button/span[contains(text(), 'Australia')]")
    private WebElement regionAustraliaButton;

    @FindBy(css = ".footer-popup-content button")
    private WebElement chooseRegionButton;


    public boolean checkAndGetTitle() {
        log.info("Проверка активности кнопки-заголовка");
        return pageTitle.isEnabled();
    }

    public int getCountElementsInSwitcher() {
        log.info("Проверка наличия элемента на странице");
        assertTrue(switcher.isEnabled(), "Свитчер доступен");
        log.info("Получение элементов свитчера");
        int count = pieceSwitcher.size();
        log.infoWithScreenshot("Кол-во элементов в свитчере: " + count);
        return count;
    }

    public void clickDemoButton() {
        log.info("Проверка наличия кнопки Demo на странице");
        assertTrue(getDemoButton.isEnabled(), "Кнопка не доступна");
        log.info("Клик по кнопке для перехода на страницу обратной связи");
        getDemoButton.click();
    }

    public void checkAndClickExploreDataButton() {
        log.info("Проверка наличия кнопки Explore data");
        assertTrue(buttonExplore.isEnabled(), "Кнопка недоступна");
        log.info("Клик по кнопки Explore data");
        buttonExplore.click();
    }

    public boolean checkVideo() {
        log.info("Проверяем наличие видеоплейера в разделе Data");
        return videoPlayerInData.get(0).isDisplayed();
    }

    public boolean cloudFreeVisible() {
        log.info("Проверяем наличие модуля Cloud Free");
        return moduleCloudFree.isDisplayed();
    }

    public boolean checkAndClickGetStarted() {
        log.info("Проверяем активность дроп даун меню Get Started");
        buttonGetStarted.isEnabled();
        log.info("Кликаем по дроп даун меню");
        buttonGetStarted.click();
        log.info("Проверяем раскрытие дроп даун меню");
        return dropDownHeader.isDisplayed();
    }

    public void checkAndSendMail(String email) {
        log.info("Проверяем доступность формы");
        footerMailForm.isDisplayed();
        log.info("Вводим email");
        footerMailForm.sendKeys(email);
        log.info("Нажимаем на кнопку подтверждения");
        footerSubmitButton.click();
    }

    public boolean checkStatementOfSubmitButton() {
        log.info("Проверяем состояние кнопки подтверждения");
        return myWait(3).checkStatement(footerMailForm);
    }


    public boolean checkTextAfterSubmitValidEmail(String expectedText) {
        log.info("Получаем текст подтверждения");
        String getText = textAfterValidMail.getText();
        log.info("Проверяем полученный текст");
        return getText.equals(expectedText);
    }

    public boolean checkTextAfterSubmitInvalidEmail(String expectedText) {
        log.info("Получаем текст ошибки");
        String getText = textAfterInvalidMail.getText();
        log.info("Проверяем полученный текст");
        return getText.equals(expectedText);
    }

    public boolean checkTextAfterSubmitEmptyEmail(String expectedText) {
        log.info("Получаем текст ошибки");
        String getText = textAfterEmptyMail.getText();
        log.info("Проверяем полученный текст");
        return getText.equals(expectedText);
    }

    public boolean checkModuleCloud() {
        log.info("Проверяем наличие модуля CLoud");
        return moduleCloud.isDisplayed();
    }

    public int checkNumberOfElementsInSwitcherCloud() {
        log.info("Получаем кол-во элементов в свитчере");
        int count = switcherCloudElements.size();
        count = count / 2;
        log.info("Кол-во элементов в свитчере: " + count);
        return count;
    }

    public boolean checkSwitcherPosition() {
        log.info("Получаем начальное положение свитчера");
        String pos = startSwitcherPosition.get(1).getText();
        log.info("Начальное положение: " + pos);
        return Objects.equals(pos, "Yearly billing");
    }

    public void checkSwitcherPositionAndClickOnMonthly() {
        log.info("Получаем начальное положение свитчера");
        String pos = startSwitcherPosition.get(1).getText();
        log.info("Начальное положение: " + pos);
        if (Objects.equals(pos, "Yearly billing")) {
            log.info("Клик по кнопке Monthly Billing");
            buttonMonthlyBilling.click();
        } else {
            log.info("Начальное положение свитчера некорректно");
        }
    }

    public boolean switcherHasSwitched() {
        log.info("Проверяем переключился ли свитчер");
        return buttonMonthlyBillingIsActive.isEnabled();
    }

    public String getAndCheckYearlyPrice() {
        log.info("Проверяем доступность элемента на странице");
        yearlyPrice.isDisplayed();
        log.info("Получаем годовую стоимость");
        String price = yearlyPrice.getText();
        log.info("Проверяем годовую стоимость");
        return price;
    }

    public String getAndCheckMonthlyPrice() {
        log.info("Проверяем доступность элемента на странице");
        monthlyPrice.isDisplayed();
        log.info("Получаем месячную стоимость");
        String price = monthlyPrice.getText();
        log.info("Обрабатываем полученный текст");
        String pr = price.trim().split("\\R")[0];
        log.info("Проверяем месячную стоимость");
        return pr;
    }

    public void checkAndClickRegionButton() {
        log.info("Проверяем, что кнопка отобразилась на странице");
        regionButton.isDisplayed();
        log.info("Кликаем по кнопке");
        regionButton.click();
    }

    public boolean popUpRegionCheck() {
        log.info("Проверяем отображение popup");
        return popUpRegion.isDisplayed();
    }

    public void clickDropDownMenuAndChooseRegionAustralia() {
        log.info("Кликаем по дроп даун меню");
        regionDropDownMenu.click();
        log.info("Выбираем регион Австралия");
        regionAustraliaButton.click();
        log.info("Клик по кнопке подтверждения выбора");
        chooseRegionButton.click();
    }

    public String checkChosenRegion() {
        log.info("Проверяем выбранный регион");
        return driver.findElement(By.xpath("//div/button[@data-test='footer-country-button']")).getText();
    }

    public DataLorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
