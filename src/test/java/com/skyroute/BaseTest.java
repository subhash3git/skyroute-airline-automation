package com.skyroute;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skyroute.pages.FlightResultsPage;
import com.skyroute.pages.FlightSearchPage;
import com.skyroute.pages.PassengerPage;

public class BaseTest {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected FlightSearchPage searchPage;

    protected FlightResultsPage resultsPage;

    protected PassengerPage passengerPage;

    protected Properties config;

    // Logger
    protected final Logger logger =
            LoggerFactory.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        logger.info("Starting test setup");

        loadConfig();

        String browser =
                config.getProperty("browser");

        logger.info(
                "Browser configured: {}",
                browser
        );

        initializeBrowser(browser);

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Initialize Page Objects
        searchPage =
                new FlightSearchPage(driver);

        resultsPage =
                new FlightResultsPage(driver);

        passengerPage =
                new PassengerPage(driver);

        logger.info(
                "WebDriver and Page Objects initialized"
        );
    }

    // Initialize browser
    private void initializeBrowser(
            String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            logger.info(
                    "Initializing Chrome browser"
            );

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("edge")) {

            logger.info(
                    "Initializing Edge browser"
            );

            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            logger.info(
                    "Initializing Firefox browser"
            );

            driver = new FirefoxDriver();

        } else {

            throw new RuntimeException(
                    "Unsupported browser: "
                    + browser
            );
        }

        logger.info(
                "Browser initialized successfully"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        logger.info("Closing browser");

        if (driver != null) {

            driver.quit();
        }

        logger.info(
                "Test cleanup completed"
        );
    }

    // Load configuration
    private void loadConfig() {

        config = new Properties();

        try (
                InputStream input =
                        getClass()
                                .getClassLoader()
                                .getResourceAsStream(
                                        "config.properties"
                                )
        ) {

            if (input == null) {

                throw new RuntimeException(
                        "config.properties not found"
                );
            }

            config.load(input);

            logger.info(
                    "Configuration loaded successfully"
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to load configuration",
                    e
            );
        }
    }

    // Open SkyRoute application
    protected void openApplication() {

        String baseUrl =
                System.getProperty(
                        "baseUrl",
                        config.getProperty("baseUrl")
                );

        logger.info(
                "Opening SkyRoute application: {}",
                baseUrl
        );

        driver.get(baseUrl);
    }

    // Search for a flight
    protected void searchFlight(
            String from,
            String to,
            String date,
            String passengers) {

        logger.info(
                "Searching flight: {} -> {} | Date: {} | Passengers: {}",
                from,
                to,
                date,
                passengers
        );

        searchPage.selectFromCity(from);

        searchPage.selectToCity(to);

        searchPage.enterDepartureDate(date);

        searchPage.selectPassengers(passengers);

        searchPage.clickSearch();

        resultsPage.waitForResults();

        logger.info(
                "Flight search completed"
        );
    }

    // Select a flight
    protected void selectFlight(
            String flightNumber) {

        logger.info(
                "Selecting flight: {}",
                flightNumber
        );

        resultsPage.selectFlight(
                flightNumber
        );

        passengerPage.waitForPassengerSection();

        logger.info(
                "Passenger section displayed"
        );
    }

    // Enter valid passenger details
    protected void enterValidPassengerDetails() {

        logger.info(
                "Entering valid passenger details"
        );

        passengerPage.enterPassengerDetails(
                "Subhash",
                "Krishna",
                "subhash@test.com",
                "9876543210"
        );
    }
}