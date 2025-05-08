# Booking API Test Automation Project

This repository contains an automated end-to-end test suite for the [Restful Booker](https://restful-booker.herokuapp.com/) API. The framework leverages Java, Cucumber (Gherkin), RestAssured, and JUnit for writing, organizing, and executing API tests in a BDD (Behavior-Driven Development) workflow.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack & Dependencies](#tech-stack--dependencies)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [How to Run the Tests](#how-to-run-the-tests)
- [Writing New Tests](#writing-new-tests)
- [Extending the Project](#extending-the-project)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## Project Overview

**Goal:**  
Provide an automated solution to create and delete bookings via the public Booking API, validating API responses and demonstrating end-to-end API automation with BDD best practices.

**Key Features:**

- Human-readable API test scenarios using Gherkin feature files.
- Step definitions implemented with Cucumber & RestAssured for HTTP calls.
- Shared test context for data like Booking ID across scenarios.
- Easily extensible for new endpoints or more complex workflows.
- Isolation of test data via JSON templates and DTOs.

---

## Tech Stack & Dependencies

- **Java 8+**
- **Maven** for dependency and build management
- **Cucumber (io.cucumber/cucumber-java, cucumber-junit, gherkin)**
- **RestAssured** for fluent HTTP request/response interaction
- **JUnit 5** as the test runner
- **Jackson** for JSON parsing
- **Selenium** included for potential browser automation (optional)

_All dependencies are managed in `pom.xml` and fetched via Maven Central._

---

## Project Structure

```
api-test-with-restassured/
│
├── pom.xml                         # Maven build file with all dependencies
├── README.md                       # Project documentation (you are here)
└── src/
    └── test/
        ├── java/
        │   ├── com/
        │   │   └── demo/
        │   │       └── test/
        │   │           ├── dto/         # Data Transfer Objects (Credentials, Token)
        │   │           └── util/        # Utilities (e.g. DateUtil)
        │   ├── runner/
        │   │   └── TestRunner.java      # Cucumber JUnit Runner
        │   └── stepdefinitions/
        │       ├── BookingApiStepDefinitions.java # Step Definitions for API scenarios
        │       └── BookingIdContext.java          # Shared test context for Booking ID
        └── resources/
            ├── features/
            │   └── BookingApi.feature   # Gherkin feature specs for Booking API
            └── json/
                └── Booking.json         # (Optional) Static payloads/templates
```

---

## Setup & Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-org/api-test-with-restassured.git
    cd api-test-with-restassured
    ```

2. **Install Java & Maven (if not already installed):**
    - [Download Java 8+](https://adoptopenjdk.net/)
    - [Install Maven](https://maven.apache.org/install.html)

3. **Install all dependencies:**
    ```bash
    mvn clean install
    ```

---

## How to Run the Tests

Run the following command from the project root:

```bash
mvn test
```

- This will launch the `runner.TestRunner` JUnit class, which triggers Cucumber, executing all steps in `BookingApi.feature`.
- Test reports will be available in the Maven `target/` directory after the run.

---

## Writing New Tests

- **Write new scenarios** in a `.feature` file inside `src/test/resources/features/`.
- Implement matching step definitions in a class within `src/test/java/stepdefinitions/`.
- Data to be shared between steps (like Booking ID) can be passed via context classes, e.g., `BookingIdContext.js`.

**Example scenario:**
```gherkin
Scenario: Retrieve a booking by ID
  Given the booking API endpoint is "https://restful-booker.herokuapp.com/booking"
  When I retrieve booking with id 123
  Then the status code should be 200
  And the response should contain "firstname"
```

---

## Extending the Project

- **Data Models:**  
  Place new DTOs under `com.demo.test.dto`.
- **Utilities:**  
  Use or add new utilities in `com.demo.test.util`.
- **Step Definitions:**  
  Organize by feature or endpoint for clarity.

**Adding new features:**  
Copy `BookingApi.feature` for template, change scenario titles, and update steps as needed.

---

## Troubleshooting

- **Test not executed?**  
  - Ensure `TestRunner.java` exists and is not excluded (should be in `src/test/java/runner/`).
  - All dependencies should resolve via Maven (`mvn clean install` to force update).

- **Step not found errors?**  
  - Double check step texts in `.feature` files exactly match annotated methods in `.java` step definitions.

- **Auth or booking fail?**  
  - Validate the API (https://restful-booker.herokuapp.com/) is live and accessible.
  - Check payload structure in step definitions matches API contract.

---

## Contributing

Pull requests are welcome! Please open an issue first to discuss any major changes.

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

**Happy Testing!**  
For questions or support, contact the maintainer or open a GitHub issue.
