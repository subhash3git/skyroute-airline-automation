package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightSearchDataDrivenTest extends BaseTest {

    @Test(
        groups = {"data-driven", "regression"},
        dataProvider = "flightSearchData",
        dataProviderClass = FlightSearchData.class,
        testName = "Flight Search - {0} to {1}"
    )
    public void flightSearchTest(
            String from,
            String to,
            String date,
            String passengers) {

        // Open application
        openApplication();

        // Search using DataProvider values
        searchFlight(
                from,
                to,
                date,
                passengers
        );

        // Verify results section
        Assert.assertTrue(
                resultsPage.areResultsDisplayed(),
                "Flight results section should be displayed"
        );

        // Get results
        String results =
                resultsPage.getFlightResultsText();

        System.out.println(
                "Route: "
                + from
                + " -> "
                + to
        );

        System.out.println(
                "Results: "
                + results
        );

        // Verify that flights are available
        Assert.assertFalse(
                results.contains(
                        "No flights available for this route."
                ),
                "Flights should be available for this route"
        );

        System.out.println(
                "DATA-DRIVEN TEST PASSED"
        );
    }
}