package com.skyroute.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightSearchPage {

    private WebDriver driver;

    private final Logger logger =
            LoggerFactory.getLogger(FlightSearchPage.class);

    // Locators
    private By fromCity = By.id("from");
    private By toCity = By.id("to");
    private By departureDate = By.id("departureDate");
    private By passengers = By.id("passengers");
    private By searchButton = By.id("searchButton");

    // Validation message
    private By searchError = By.id("searchError");

    // Constructor
    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Select From city
    public void selectFromCity(String city) {

        logger.info(
                "Selecting From city: {}",
                city
        );

        Select fromDropdown =
                new Select(
                        driver.findElement(fromCity)
                );

        fromDropdown.selectByVisibleText(city);
    }

    // Select To city
    public void selectToCity(String city) {

        logger.info(
                "Selecting To city: {}",
                city
        );

        Select toDropdown =
                new Select(
                        driver.findElement(toCity)
                );

        toDropdown.selectByVisibleText(city);
    }

    // Enter departure date
    public void enterDepartureDate(String date) {

        logger.info(
                "Entering departure date: {}",
                date
        );

        driver.findElement(departureDate)
                .sendKeys(date);
    }

    // Select passengers
    public void selectPassengers(String count) {

        logger.info(
                "Selecting passengers: {}",
                count
        );

        Select passengerDropdown =
                new Select(
                        driver.findElement(passengers)
                );

        passengerDropdown.selectByVisibleText(count);
    }

    // Click Search
    public void clickSearch() {

        logger.info("Clicking Search button");

        driver.findElement(searchButton)
                .click();
    }

    // Get search validation message
    public String getSearchError() {

        String error =
                driver.findElement(searchError)
                        .getText();

        logger.info(
                "Search validation message: {}",
                error
        );

        return error;
    }

    // Get selected From city
    public String getFromCityValue() {

        return driver
                .findElement(fromCity)
                .getAttribute("value");
    }

    // Get selected To city
    public String getToCityValue() {

        return driver
                .findElement(toCity)
                .getAttribute("value");
    }
}