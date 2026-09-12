package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightSearchSameCityTest extends BaseTest {

    @Test(groups = {"validation", "regression"})
    public void sameCityValidationTest() {

        logger.info("Starting same-city validation test");

        openApplication();

        searchPage.selectFromCity("Delhi");
        searchPage.selectToCity("Delhi");
        searchPage.enterDepartureDate("2026-09-12");
        searchPage.selectPassengers("1");
        searchPage.clickSearch();

        String actualMessage =
                searchPage.getSearchError();

        logger.info(
                "Actual same-city validation message: {}",
                actualMessage
        );

        String expectedMessage =
                "From and To cities cannot be the same.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect same-city validation message"
        );

        logger.info(
                "TC003 PASSED - Same From and To city validation works"
        );
    }
}