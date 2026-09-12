package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightPassengerInvalidPhoneTest extends BaseTest {

    @Test(groups = {"validation", "regression"})
    public void invalidPhoneValidationTest() {

        logger.info(
                "Starting invalid phone validation test"
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
        passengerPage.enterEmail("subhash@test.com");
        passengerPage.enterPhone("12345");

        passengerPage.clickContinue();

        String actualMessage =
                passengerPage.getPassengerError();

        logger.info(
                "Actual phone validation message: {}",
                actualMessage
        );

        String expectedMessage =
                "Phone number must contain 10 digits.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect phone validation message"
        );

        logger.info(
                "TC007 PASSED - Invalid phone validation works"
        );
    }
}