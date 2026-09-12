# SkyRoute Airline Automation Framework

A Java-based Selenium automation testing framework developed to automate functional and validation testing for the SkyRoute Airline Reservation System.

The framework uses Selenium WebDriver, TestNG, Page Object Model (POM), Data-Driven Testing, Extent Reports, SLF4J/Logback logging, screenshots, configuration management, and Maven.

---

## 🚀 Project Overview

The SkyRoute Airline Automation Framework automates key workflows of an airline reservation application, including:

- Flight search
- Search field validation
- Same-city validation
- No-flight availability validation
- Passenger information validation
- Invalid email validation
- Invalid phone validation
- Seat selection validation
- Booked-seat verification
- End-to-end flight booking
- Data-driven flight search testing

The framework is designed with maintainability, reusability, reporting, and scalable test execution in mind.

---

## 🛠️ Technologies & Tools

| Technology / Tool | Purpose |
|---|---|
| Java 24 | Programming language |
| Selenium WebDriver | Web UI automation |
| TestNG | Test execution and test organization |
| Maven | Build and dependency management |
| Page Object Model | Framework design pattern |
| Extent Reports | HTML test reporting |
| SLF4J + Logback | Logging |
| Git | Version control |
| GitHub | Source code repository |
| VS Code | Development environment |

---

## 🏗️ Framework Architecture

```text
airline-automation
│
├── .mvn/
│
├── src/
│   ├── main/
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── skyroute/
│       │           │
│       │           ├── BaseTest.java
│       │           ├── TestListener.java
│       │           ├── FlightSearchData.java
│       │           │
│       │           ├── FlightSearchTest.java
│       │           ├── FlightSearchValidationTest.java
│       │           ├── FlightSearchSameCityTest.java
│       │           ├── FlightSearchNoFlightsTest.java
│       │           ├── FlightSearchDataDrivenTest.java
│       │           │
│       │           ├── FlightPassengerValidationTest.java
│       │           ├── FlightPassengerInvalidEmailTest.java
│       │           ├── FlightPassengerInvalidPhoneTest.java
│       │           │
│       │           ├── FlightSeatValidationTest.java
│       │           ├── FlightBookedSeatsTest.java
│       │           │
│       │           ├── pages/
│       │           │   ├── FlightSearchPage.java
│       │           │   ├── FlightResultsPage.java
│       │           │   ├── PassengerPage.java
│       │           │   ├── SeatSelectionPage.java
│       │           │   └── BookingConfirmationPage.java
│       │           │
│       │           └── utils/
│       │               └── ScreenshotUtil.java
│       │
│       └── resources/
│           ├── config.properties
│           └── logback.xml
│
├── testng.xml
├── testng-smoke.xml
├── testng-regression.xml
├── pom.xml
├── .gitignore
└── README.md