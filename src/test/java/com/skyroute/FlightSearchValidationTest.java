package com.skyroute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightSearchValidationTest extends BaseTest {

    @Test(groups = {"validation", "regression"})
    public void mandatoryFieldValidationTest() {

        logger.info("Starting mandatory field validation test");

        openApplication();

        searchPage.clickSearch();

        String actualMessage =
                searchPage.getSearchError();

        logger.info(
                "Actual validation message: {}",
                actualMessage
        );

        String expectedMessage =
                "Please select From, To and Departure Date.";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Incorrect mandatory field validation message"
        );

        logger.info(
                "TC002 PASSED - Mandatory field validation works"
        );
    }
}