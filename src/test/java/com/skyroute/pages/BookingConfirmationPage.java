package com.skyroute.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingConfirmationPage {

    private WebDriver driver;

    private WebDriverWait wait;

    private final Logger logger =
            LoggerFactory.getLogger(BookingConfirmationPage.class);

    // Locators
    private By confirmationSection =
            By.id("confirmationSection");

    private By pnr =
            By.id("pnr");

    private By confirmationFlight =
            By.id("confirmationFlight");

    private By confirmationPassenger =
            By.id("confirmationPassenger");

    private By confirmationSeat =
            By.id("confirmationSeat");

    private By confirmationPrice =
            By.id("confirmationPrice");

    // Constructor
    public BookingConfirmationPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );
    }

    // Wait for booking confirmation
    public void waitForConfirmation() {

        logger.info(
                "Waiting for booking confirmation"
        );

        wait.until(
                driver ->
                        driver.findElement(
                                confirmationSection
                        ).isDisplayed()
        );

        logger.info(
                "Booking confirmation displayed"
        );
    }

    // Check whether booking confirmation is displayed
    public boolean isBookingConfirmed() {

        boolean confirmed =
                driver.findElement(
                        confirmationSection
                ).isDisplayed();

        logger.info(
                "Booking confirmation displayed: {}",
                confirmed
        );

        return confirmed;
    }

    // Get PNR
    public String getPNR() {

        String bookingPNR =
                driver.findElement(pnr)
                        .getText();

        logger.info(
                "Booking PNR retrieved: {}",
                bookingPNR
        );

        return bookingPNR;
    }

    // Get confirmed flight
    public String getConfirmedFlight() {

        String flight =
                driver.findElement(
                        confirmationFlight
                ).getText();

        logger.info(
                "Confirmed flight: {}",
                flight
        );

        return flight;
    }

    // Get passenger name
    public String getPassengerName() {

        String passenger =
                driver.findElement(
                        confirmationPassenger
                ).getText();

        logger.info(
                "Confirmed passenger: {}",
                passenger
        );

        return passenger;
    }

    // Get confirmed seat
    public String getConfirmedSeat() {

        String seat =
                driver.findElement(
                        confirmationSeat
                ).getText();

        logger.info(
                "Confirmed seat: {}",
                seat
        );

        return seat;
    }

    // Get confirmed price
    public String getConfirmedPrice() {

        String price =
                driver.findElement(
                        confirmationPrice
                ).getText();

        logger.info(
                "Confirmed booking price: {}",
                price
        );

        return price;
    }
}