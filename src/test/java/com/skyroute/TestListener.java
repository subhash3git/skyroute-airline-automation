package com.skyroute;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.skyroute.utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private final Logger logger =
            LoggerFactory.getLogger(TestListener.class);

    private static ExtentReports extentReports;

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    // Initialize Extent Report
    private static synchronized ExtentReports getExtentReports() {

        if (extentReports == null) {

            String reportPath =
                    "test-output/SkyRoute-Automation-Report.html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config()
                    .setDocumentTitle(
                            "SkyRoute Automation Report"
                    );

            sparkReporter.config()
                    .setReportName(
                            "SkyRoute Airline Automation Test Report"
                    );

            extentReports =
                    new ExtentReports();

            extentReports.attachReporter(
                    sparkReporter
            );

            extentReports.setSystemInfo(
                    "Application",
                    "SkyRoute Airline Reservation System"
            );

            extentReports.setSystemInfo(
                    "Framework",
                    "Selenium + TestNG"
            );

            extentReports.setSystemInfo(
                    "Java Version",
                    System.getProperty("java.version")
            );

            extentReports.setSystemInfo(
                    "Operating System",
                    System.getProperty("os.name")
            );

            extentReports.setSystemInfo(
                    "Browser",
                    getBrowser()
            );
        }

        return extentReports;
    }

    private static String getBrowser() {

        try {

            return new java.util.Properties() {{
                load(
                    TestListener.class
                        .getClassLoader()
                        .getResourceAsStream(
                            "config.properties"
                        )
                );
            }}.getProperty(
                    "browser",
                    "Unknown"
            );

        } catch (Exception e) {

            return "Unknown";
        }
    }

    @Override
    public void onTestStart(
            ITestResult result) {

        logger.info(
                "========================================"
        );

        logger.info(
                "TEST STARTED: {}",
                result.getMethod()
                        .getMethodName()
        );

        logger.info(
                "========================================"
        );

        ExtentTest test =
                getExtentReports()
                        .createTest(
                                result.getMethod()
                                        .getMethodName()
                        );

        extentTest.set(test);

        extentTest.get().info(
                "Test execution started"
        );

        extentTest.get().info(
                "Browser: " + getBrowser()
        );
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        logger.info(
                "TEST PASSED: {}",
                result.getMethod()
                        .getMethodName()
        );

        extentTest.get().pass(
                "Test passed successfully"
        );
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        logger.error(
                "TEST FAILED: {}",
                testName
        );

        String failureMessage =
                result.getThrowable()
                        .getMessage();

        logger.error(
                "Failure reason: {}",
                failureMessage
        );

        Object testClass =
                result.getInstance();

        BaseTest baseTest =
                (BaseTest) testClass;

        String screenshotPath =
                ScreenshotUtil.captureScreenshot(
                        baseTest.driver,
                        testName
                );

        logger.info(
                "Failure screenshot saved: {}",
                screenshotPath
        );

        extentTest.get().fail(
                "Test failed"
        );

        extentTest.get().fail(
                "Failure reason: "
                + failureMessage
        );

        if (screenshotPath != null) {

            try {

                extentTest.get()
                        .addScreenCaptureFromPath(
                                new File(
                                        screenshotPath
                                )
                                .getAbsolutePath()
                        );

            } catch (Exception e) {

                logger.error(
                        "Unable to attach screenshot to Extent Report",
                        e
                );
            }
        }
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        logger.warn(
                "TEST SKIPPED: {}",
                result.getMethod()
                        .getMethodName()
        );

        if (extentTest.get() != null) {

            extentTest.get().skip(
                    "Test skipped"
            );
        }
    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        logger.info(
                "Finalizing Extent Report"
        );

        if (extentReports != null) {

            extentReports.flush();
        }

        logger.info(
                "Extent Report generated successfully"
        );
    }
}