package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightPassengerInvalidEmailTest extends BaseTest {

    @Test(groups = {"validation", "regression"})
    public void invalidEmailValidationTest() {

        logger.info(
                "Starting invalid email validation test"
        );

        openApplication();

        searchFlight(
                "Bengaluru",
                "Delhi",
                "2026-09-12",
                "1"
        );

        selectFlight("SR101");

        passengerPage.enterFirstName("Subhash");
        passengerPage.enterLastName("Krishna");
        passengerPage.enterEmail("invalid-email");
        passengerPage.enterPhone("9876543210");

        passengerPage.clickContinue();

        String actualMessage =
                passengerPage.getPassengerError();

        logger.info(
                "Actual email validation message: {}",
                actualMessage
        );

        String expectedMessage =
                "Please enter a valid email address.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect email validation message"
        );

        logger.info(
                "TC006 PASSED - Invalid email validation works"
        );
    }
}