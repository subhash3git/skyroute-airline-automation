package com.skyroute.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightResultsPage {

    private WebDriver driver;

    private WebDriverWait wait;

    private final Logger logger =
            LoggerFactory.getLogger(FlightResultsPage.class);

    private By resultsSection =
            By.id("flightResultsSection");

    private By flightResults =
            By.id("flightResults");

    public FlightResultsPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );
    }

    // Wait for flight results
    public void waitForResults() {

        logger.info(
                "Waiting for flight results section"
        );

        wait.until(
                driver ->
                        driver.findElement(
                                resultsSection
                        ).isDisplayed()
        );

        logger.info(
                "Flight results section displayed"
        );
    }

    // Check whether results are displayed
    public boolean areResultsDisplayed() {

        boolean displayed =
                driver.findElement(
                        resultsSection
                ).isDisplayed();

        logger.info(
                "Flight results displayed: {}",
                displayed
        );

        return displayed;
    }

    // Get flight results text
    public String getFlightResultsText() {

        String results =
                driver.findElement(
                        flightResults
                ).getText();

        logger.info(
                "Flight results retrieved"
        );

        return results;
    }

    // Select a flight
    public void selectFlight(String flightNumber) {

        logger.info(
                "Selecting flight: {}",
                flightNumber
        );

        By selectButton =
                By.cssSelector(
                        "button.select-flight[data-flight-number='"
                        + flightNumber
                        + "']"
                );

        wait.until(
                driver ->
                        driver.findElement(
                                selectButton
                        ).isDisplayed()
        );

        driver.findElement(selectButton)
                .click();

        logger.info(
                "Flight {} selected successfully",
                flightNumber
        );
    }

    // Get no-flights message
    public String getNoFlightsMessage() {

        String message =
                driver.findElement(
                        flightResults
                ).getText();

        logger.info(
                "No-flights/result message: {}",
                message
        );

        return message;
    }
}