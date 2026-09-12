package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skyroute.pages.BookingConfirmationPage;
import com.skyroute.pages.SeatSelectionPage;

public class FlightSearchTest extends BaseTest {

    private SeatSelectionPage seatPage;
    private BookingConfirmationPage confirmationPage;

    @Test(groups = {"smoke", "regression"})
    public void completeBookingTest() {

        logger.info("Starting complete booking test");

        openApplication();

        searchFlight(
                "Bengaluru",
                "Delhi",
                "2026-09-12",
                "1"
        );

        String resultsText =
                resultsPage.getFlightResultsText();

        Assert.assertTrue(
                resultsText.contains("SR101"),
                "SR101 should be displayed"
        );

        Assert.assertTrue(
                resultsText.contains("SR102"),
                "SR102 should be displayed"
        );

        logger.info(
                "Required flights SR101 and SR102 are displayed"
        );

        selectFlight("SR101");

        enterValidPassengerDetails();

        passengerPage.clickContinue();

        seatPage =
                new SeatSelectionPage(driver);

        seatPage.waitForSeatSection();

        seatPage.selectSeat12();

        seatPage.clickConfirmBooking();

        confirmationPage =
                new BookingConfirmationPage(driver);

        confirmationPage.waitForConfirmation();

        Assert.assertTrue(
                confirmationPage.isBookingConfirmed(),
                "Booking confirmation should be displayed"
        );

        String pnr =
                confirmationPage.getPNR();

        Assert.assertTrue(
                pnr.startsWith("SR"),
                "PNR should start with SR"
        );

        Assert.assertEquals(
                pnr.length(),
                8,
                "PNR should contain 8 characters"
        );

        Assert.assertEquals(
                confirmationPage.getConfirmedFlight(),
                "SR101",
                "Incorrect confirmed flight"
        );

        Assert.assertEquals(
                confirmationPage.getPassengerName(),
                "Subhash Krishna",
                "Incorrect passenger name"
        );

        Assert.assertEquals(
                confirmationPage.getConfirmedSeat(),
                "12",
                "Incorrect confirmed seat"
        );

        logger.info("====================================");
        logger.info("BOOKING TEST PASSED");
        logger.info("PNR: {}", pnr);
        logger.info(
                "Flight: {}",
                confirmationPage.getConfirmedFlight()
        );
        logger.info(
                "Passenger: {}",
                confirmationPage.getPassengerName()
        );
        logger.info(
                "Seat: {}",
                confirmationPage.getConfirmedSeat()
        );
        logger.info("====================================");
    }
}