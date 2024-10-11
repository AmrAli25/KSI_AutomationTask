This is a task for KSI to automate two test cases Using PlayWright with Java:
* Product Catalog Management:
  - Automate tests to verify the search functionality for products using different search criteria (e.g., by category, by keyword).
  - Verify the sorting and filtering options on product listings.
* TC2: Shopping Cart and Checkout Process:
  - Implement automated tests to simulate adding products to the shopping cart.
  - Verify the calculation of total prices, applying discounts (if any), and handling of promotional codes.
  - Automate tests for the checkout process, including selecting shipping options and payment methods.

# Project Details:
## Main Framework used:
* [Playwright](https://playwright.dev/java/) <br><img height="100" title="Playwright" src="https://playwright.dev/java/img/playwright-logo.svg">
## Website used:
* [Amazon.eg](https://amazon.eg/-/en) 
## Project Design:
* Page Object Model (POM) design pattern.
* Fluent design approach (method chaining) for better test case readability.x
* TestNG framework as a testing framework
* Allure reports to provide a comprehensive report about the test cases and their status

## How to run the project (Prerequisites):
* Java 23 should be installed to be able to run this project or you can change the version from the pom.xml file to the one you want
* Using [Maven](https://maven.apache.org) installed on your machine
  - Clone the repo on your machine
  - run the command "mvn verify" in an opened terminal in the project directory
* Using [InteliJ IDE](https://www.jetbrains.com/idea/) to run it through the Code Editor
  - Clone the project and open it in the IDE
  - From Maven lifeCycle choose the option "verify"
* To Generate the report file for the test cases using [Allure](https://allurereport.org/docs)
  - Install Allure
  - After runnig the test cases using a terminal or IDE
  - Open a terminal in the file "target" in the project directory or using the IDE terminal with the command "cd target"
  - Run the command "allure serve" to host the report on a local server in the browser or "allure generate" to generate a folder (allure-report) located in "target" and open "index.html"
