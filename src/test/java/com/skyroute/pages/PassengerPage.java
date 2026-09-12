package com.skyroute.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PassengerPage {

    private WebDriver driver;

    private WebDriverWait wait;

    private final Logger logger =
            LoggerFactory.getLogger(PassengerPage.class);

    // Locators
    private By passengerSection =
            By.id("passengerSection");

    private By firstName =
            By.id("firstName");

    private By lastName =
            By.id("lastName");

    private By email =
            By.id("email");

    private By phone =
            By.id("phone");

    private By continueButton =
            By.id("continueButton");

    private By passengerError =
            By.id("passengerError");

    // Constructor
    public PassengerPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );
    }

    // Wait for passenger section
    public void waitForPassengerSection() {

        logger.info(
                "Waiting for passenger section"
        );

        wait.until(
                driver ->
                        driver.findElement(
                                passengerSection
                        ).isDisplayed()
        );

        logger.info(
                "Passenger section displayed"
        );
    }

    // Enter first name
    public void enterFirstName(String name) {

        logger.info(
                "Entering first name"
        );

        driver.findElement(firstName)
                .sendKeys(name);
    }

    // Enter last name
    public void enterLastName(String name) {

        logger.info(
                "Entering last name"
        );

        driver.findElement(lastName)
                .sendKeys(name);
    }

    // Enter email
    public void enterEmail(String emailAddress) {

        logger.info(
                "Entering passenger email"
        );

        driver.findElement(email)
                .sendKeys(emailAddress);
    }

    // Enter phone
    public void enterPhone(String phoneNumber) {

        logger.info(
                "Entering passenger phone number"
        );

        driver.findElement(phone)
                .sendKeys(phoneNumber);
    }

    // Click Continue
    public void clickContinue() {

        logger.info(
                "Clicking Continue button"
        );

        driver.findElement(continueButton)
                .click();

        logger.info(
                "Continue button clicked"
        );
    }

    // Enter complete passenger details
    public void enterPassengerDetails(
            String firstName,
            String lastName,
            String email,
            String phone) {

        logger.info(
                "Entering complete passenger details"
        );

        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhone(phone);

        logger.info(
                "Passenger details entered successfully"
        );
    }

    // Get passenger validation message
    public String getPassengerError() {

        String error =
                driver.findElement(
                        passengerError
                ).getText();

        logger.info(
                "Passenger validation message: {}",
                error
        );

        return error;
    }
}