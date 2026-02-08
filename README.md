# RestAssured-Framework

A modern **API test automation framework** built with **Java** and **REST Assured**.  
It provides reusable request/response specifications, structured endpoint classes, and rich reporting with **Extent Reports**.

---

## 🚀 Project Purpose

This framework is designed for automated testing of RESTful APIs in a structured, maintainable, and reusable way.  
**code-driven tests** for flexible test automation.

---

## 🛠️ Prerequisites

- Java JDK 11 or higher
- Maven 3.x
- IDE (IntelliJ IDEA, Eclipse, etc.)
- Optional: Cucumber or TestNG plugins for IDE

---

## 📥 Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/spy235/RestAssured-Framework.git
cd RestAssured-Framework
````

2. Install dependencies and run tests using Maven:

```bash
mvn clean test
```

---

## 📁 Project Structure

```
src/main/java/         - Core framework classes (request/response specs, utils)
src/test/java/         - Test classes, service classes, and base classes
src/test/resources/    - Cucumber feature files, test data
reports/               - Generated test reports (Extent/Allure)
pom.xml                - Maven dependencies and build configuration
testng.xml             - TestNG configuration (optional)
```

---

## 🧪 Running Tests

* Run all tests:

```bash
mvn clean test
```

* Run specific TestNG groups:

```bash
mvn test -Dgroups="login"
```

* Run Cucumber tests with tags:

```bash
mvn test -Dcucumber.options="--tags @smoke"
```

---

## 📊 Reporting

* **Extent Reports**: HTML report generated in `reports/extent/` after test execution.
* **Allure Reports**: Generate by:

```bash
allure serve target/allure-results
```

---

## ⚙️ Configuration

* **Base URI, credentials, and environment variables** are configured in the `ConfigReader` class or `application.properties`.
* Modify `requestSpec` or `responseSpec` in `SpecBuilders` for global API request/response settings.

---

## 🤝 Contribution

1. Fork the repository
2. Create a new branch
3. Make changes or add new tests
4. Submit a pull request

---

## 📜 Notes

* Default request and response specifications are **global** and can be used across all tests with `RestAssured.given()`.
* Ensure POJOs (request/response models) match API payloads exactly for deserialization.

---

## 💡 References

* [REST Assured Documentation](https://rest-assured.io/)
* [Cucumber BDD](https://cucumber.io/docs)
* [TestNG](https://testng.org/doc/)
* [Extent Reports](https://extentreports.com/)
* [Allure Report](https://docs.qameta.io/allure/)
