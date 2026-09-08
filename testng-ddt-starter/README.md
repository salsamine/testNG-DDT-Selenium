# TestNG + Selenium Data-Driven Testing

Automation testing project for SauceDemo login functionality using Selenium, TestNG, and Data-Driven Testing (DDT).

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Apache POI
- Microsoft Excel

## Test Scenarios

This project contains the following test scenarios:

1. Hard Assertion
2. Soft Assertion
3. Data-Driven Login Test
    - Valid username and password → SUCCESS
    - Invalid username → INVALID_USERNAME
    - Invalid password → INVALID_PASSWORD

## Data-Driven Testing

Test data is stored in:

`src/test/resources/testdata/login-data.xlsx`

The Excel file contains:

| Test Case | Username | Password | Expected Result |
|-----------|----------|----------|-----------------|
| TC001 | standard_user | secret_sauce | SUCCESS |
| TC002 | 123 | secret_sauce | INVALID_USERNAME |
| TC003 | visual_user | 123 | INVALID_PASSWORD |

Test data is read using Apache POI and supplied to the test method using TestNG `@DataProvider`.

## Project Structure

```text
testng-ddt-starter/
├── src/
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   │   └── LoginTest.java
│       │   └── utils/
│       │       └── ExcelReader.java
│       └── resources/
│           └── testdata/
│               └── login-data.xlsx
├── docs_testNG_DDT_assignment/
│   └── test-result.png
├── pom.xml
└── testng.xml
```

## How to Run

1. Clone this repository.
2. Open the `testng-ddt-starter` folder as a Maven project.
3. Make sure Java 17 is installed.
4. Install/download Maven dependencies from `pom.xml`.
5. Run `testng.xml`.

## Test Result

The TestNG suite successfully executed:

- Total Tests: 5
- Passed: 5
- Failed: 0
- Skipped: 0

The test execution includes 3 Data-Driven Login Test scenarios, Hard Assertion, and Soft Assertion.

![TestNG Test Result](docs_testNG_DDT_assignment/test-result.png)