package com.skyroute;

import org.testng.annotations.DataProvider;

public class FlightSearchData {

    @DataProvider(name = "flightSearchData")
    public Object[][] flightSearchData() {

        return new Object[][] {
                {"Bengaluru", "Delhi", "2026-09-12", "1"},
                {"Bengaluru", "Mumbai", "2026-09-12", "1"},
                {"Delhi", "Mumbai", "2026-09-12", "1"},
                {"Hyderabad", "Chennai", "2026-09-12", "1"}
        };
    }
}