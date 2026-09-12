package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightSearchNoFlightsTest extends BaseTest {

    @Test(groups = {"validation", "regression"})
    public void noFlightsAvailableTest() {

        logger.info("Starting no-flights-available test");

        openApplication();

        searchFlight(
                "Bengaluru",
                "Chennai",
                "2026-09-12",
                "1"
        );

        Assert.assertTrue(
                resultsPage.areResultsDisplayed(),
                "Flight results section should be displayed"
        );

        String actualMessage =
                resultsPage.getNoFlightsMessage();

        logger.info(
                "Actual no-flights message: {}",
                actualMessage
        );

        String expectedMessage =
                "No flights available for this route.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect no-flights message"
        );

        logger.info(
                "TC004 PASSED - No flights available validation works"
        );
    }
}