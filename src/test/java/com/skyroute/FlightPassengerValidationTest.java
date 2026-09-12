package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightPassengerValidationTest extends BaseTest {

    @Test(groups = {"validation", "regression"})
    public void passengerMandatoryValidationTest() {

        logger.info(
                "Starting passenger mandatory validation test"
        );

        openApplication();

        searchFlight(
                "Bengaluru",
                "Delhi",
                "2026-09-12",
                "1"
        );

        selectFlight("SR101");

        passengerPage.clickContinue();

        String actualMessage =
                passengerPage.getPassengerError();

        logger.info(
                "Actual passenger validation message: {}",
                actualMessage
        );

        String expectedMessage =
                "All passenger fields are mandatory.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect passenger mandatory validation message"
        );

        logger.info(
                "TC005 PASSED - Passenger mandatory field validation works"
        );
    }
}