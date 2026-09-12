package com.skyroute.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeatSelectionPage {

    private WebDriver driver;

    private WebDriverWait wait;

    private final Logger logger =
            LoggerFactory.getLogger(SeatSelectionPage.class);

    // Locators
    private By seatSection =
            By.id("seatSection");

    private By seat12 =
            By.cssSelector("[data-seat-number='12']");

    private By confirmButton =
            By.id("confirmButton");

    private By seatError =
            By.id("seatError");

    // Constructor
    public SeatSelectionPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );
    }

    // Wait for seat selection section
    public void waitForSeatSection() {

        logger.info(
                "Waiting for seat selection section"
        );

        wait.until(
                driver ->
                        driver.findElement(
                                seatSection
                        ).isDisplayed()
        );

        logger.info(
                "Seat selection section displayed"
        );
    }

    // Select seat 12
    public void selectSeat12() {

        logger.info(
                "Selecting seat 12"
        );

        driver.findElement(seat12)
                .click();

        logger.info(
                "Seat 12 selected successfully"
        );
    }

    // Click Confirm Booking
    public void clickConfirmBooking() {

        logger.info(
                "Clicking Confirm Booking button"
        );

        driver.findElement(confirmButton)
                .click();

        logger.info(
                "Confirm Booking button clicked"
        );
    }

    // Get seat validation message
    public String getSeatError() {

        String error =
                driver.findElement(
                        seatError
                ).getText();

        logger.info(
                "Seat validation message: {}",
                error
        );

        return error;
    }

    // Check whether a seat is disabled/booked
    public boolean isSeatDisabled(
            String seatNumber) {

        logger.info(
                "Checking whether seat {} is booked",
                seatNumber
        );

        By seat =
                By.cssSelector(
                        "[data-seat-number='"
                        + seatNumber
                        + "']"
                );

        boolean disabled =
                driver.findElement(seat)
                        .getAttribute("disabled") != null;

        logger.info(
                "Seat {} booked status: {}",
                seatNumber,
                disabled
        );

        return disabled;
    }
}