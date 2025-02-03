package com.example.jetbrainstest.pages.datalorepages;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

public class DataConnectionsPage {
    WebDriver driver;
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(DataConnectionsPage.class));

    @FindBy(xpath = "//div/h1")
    private WebElement headerDataConnectionsPage;


    public boolean checkDataTitle() {
       return headerDataConnectionsPage.isDisplayed();
    }

    public DataConnectionsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
