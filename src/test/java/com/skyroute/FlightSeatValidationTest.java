package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skyroute.pages.SeatSelectionPage;

public class FlightSeatValidationTest extends BaseTest {

    private SeatSelectionPage seatPage;

    @Test(groups = {"validation", "regression"})
    public void seatMandatoryValidationTest() {

        logger.info(
                "Starting seat mandatory validation test"
        );

        openApplication();

        searchFlight(
                "Bengaluru",
                "Delhi",
                "2026-09-12",
                "1"
        );

        selectFlight("SR101");

        enterValidPassengerDetails();

        passengerPage.clickContinue();

        seatPage =
                new SeatSelectionPage(driver);

        seatPage.waitForSeatSection();

        logger.info(
                "Seat selection section is ready"
        );

        seatPage.clickConfirmBooking();

        String actualMessage =
                seatPage.getSeatError();

        logger.info(
                "Actual seat validation message: {}",
                actualMessage
        );

        String expectedMessage =
                "Please select a seat.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect seat validation message"
        );

        logger.info(
                "TC008 PASSED - Seat mandatory validation works"
        );
    }
}