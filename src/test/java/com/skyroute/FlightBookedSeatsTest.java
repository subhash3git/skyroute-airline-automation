package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skyroute.pages.SeatSelectionPage;

public class FlightBookedSeatsTest extends BaseTest {

    private SeatSelectionPage seatPage;

    @Test(groups = {"smoke", "regression"})
    public void bookedSeatsVerificationTest() {

        logger.info(
                "Starting booked seats verification test"
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
                "Checking booked seats: 3, 7, 14, 21"
        );

        Assert.assertTrue(
                seatPage.isSeatDisabled("3"),
                "Seat 3 should be booked"
        );

        Assert.assertTrue(
                seatPage.isSeatDisabled("7"),
                "Seat 7 should be booked"
        );

        Assert.assertTrue(
                seatPage.isSeatDisabled("14"),
                "Seat 14 should be booked"
        );

        Assert.assertTrue(
                seatPage.isSeatDisabled("21"),
                "Seat 21 should be booked"
        );

        logger.info(
                "TC009 PASSED - Booked seats are disabled"
        );
    }
}