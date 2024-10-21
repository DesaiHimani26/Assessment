# Automation Tests for API and UI 

## About 
This repositry contains : 
1. Task 1: Documentation for [Test Plan ](https://github.com/DesaiHimani26/Assessment/blob/main/TestPlan.md)
2. Task 2: Documentation for [Prioretization](https://github.com/DesaiHimani26/Assessment/blob/main/Prioritization.md) 
3. Task 3: Automated tests for [GoRest](https://gorest.co.in/) (API)
4. Task 4: Automated tests for Sales flow for [Eneco](https://www.eneco.nl/) (UI)




## Table of Contents

- [Project Structure](#project-structure)
- [Dependencies and Prerequisites](#dependencies-and-prerequisites)
- [Setup Instructions](#setup-instructions)
  - [Environment Variables](#2-environment-variables)
  - [Install Dependencies](#3-install-dependencies)
  - [Run Tests](#4-run-the-tests)
- [GitHub Actions for CI](#5-github-actions-for-ci)
- [Test Reporting and Artifacts](#6-test-reporting-and-artifacts)
- [Improvements](#improvements)

---

## Project Structure
```
Assessment/
├── cypress/
|   ├── e2e/               
│       └── apiTests/      # API test cases
│       └── uiTests/       # UI test cases
│   ├── fixtures/          # Sample test and static data
│   ├── pageObjects/       
│       └── apiObjects/    # Helper and Page classes for API endpoints
│       └── uiObjects/     # Helper and Page classes for different UI pages
│   ├── support/           # Cypress custom commands and global configurations
│       └── utils/         # Class with utility functions for API
│   ├── reports/           
│       └── html/          # Generated HTML test reports (e.g., mochawesome)
│       └── video/         # Test Execution Video for apiTests and uiTests
├── .github/
│   └── workflows/         # GitHub Actions CI workflow
├── .env                   # Environment variable configurations
├── cypress.config.js      # Cypress configuration file
├── package.json           # Node.js dependencies and scripts
└── Prioritization.md      # Documentation for Task 2
└── README.md              # Project documentation
└── TestPlan.md            # Documentation for Task 1

```

### Test Suite Structure
- **UI Tests**: Located in `cypress/e2e/uiTests`, they validate the front-end elements of the application.
- **API Tests**: Located in `cypress/e2e/apiTests`, they validate the back-end API responses using `cypress-plugin-api`.
- **Test Data**: Located in `cypress/fixtures`, which stores JSON files to simulate user input, and other static information. Also, `faker` library is used to generate dynamic test data.
- **Plugins/Support**: Custom Cypress [commands](https://github.com/DesaiHimani26/Assessment/blob/main/cypress/support/commands.js) and plugins to extend functionality.

## Dependencies and Prerequisites

The following software is required to run the tests locally. You can download it from the respective links:
- [Node.js](https://nodejs.org/) (version 12.x or higher)
- [npm](https://www.npmjs.com/) (comes with Node.js)
- [Git](https://git-scm.com/) (for cloning the repository)

Optional but recommended:
- [Cypress](https://www.cypress.io/) (can be installed globally)
- [Chrome](https://www.google.com/chrome/) or other chromium based browser

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/DesaiHimani26/Assessment.git
cd Assessment
```

### 2. Environment Variables
1. Create a access token for GoRest API if does NOT have already. See [Get your access token](https://gorest.co.in/consumer/login)
2. Replace value of ACCES_TOKEN in [.env](https://github.com/DesaiHimani26/Assessment/blob/main/.env) file with the value of your Access Token copied from [here](https://gorest.co.in/my-account/access-tokens)

### 3. Install Dependencies
Run the following command to install all necessary dependencies:
```bash
npm install
```
This will install Cypress, mochawesome, cypress-plugin-api, faker, and other dependencies.

### 4. Run the Tests
To run all the test (UI and API) in Headless mode
```bash
npx cypress run
```

To run in Headed mode with Chrome Browser:
```bash
npx cypress run --browser chrome --headed
```

To run only API tests
```bash
npx cypress run --spec cypress/e2e/apiTests/*.cy.js
```

To run only UI tests
```bash
npx cypress run --spec cypress/e2e/uiTests/*.cy.js
```

### 5. GitHub Actions for CI
This repository uses [GitHub Actions](https://github.com/DesaiHimani26/Assessment/actions) to automate testing. The workflow file is located in .github/workflows/[ci.yml](https://github.com/DesaiHimani26/Assessment/blob/main/.github/workflows/ci.yml). It is triggered automatically on every push to run both UI and API tests.

### 6. Test Reporting and Artifacts
The test suite uses the mochawesome reporter to generate detailed reports. After running the tests, the reports will be generated in the cypress/reports directory.

To view the report after running tests on Windows:
```bash
start cypress/reports/html/index.html
```
To view the report after running tests on macOS:
```bash
open cypress/reports/html/index.html
```

For CI, each pipeline trigger has corresponding HTML report and video, under Workflow Run > [Artifacts](https://github.com/DesaiHimani26/Assessment/actions/runs/11430842735/artifacts/2079974507) e.g. See [here](https://github.com/DesaiHimani26/Assessment/actions/runs/11430325504)

### Improvements:
- Different repository Setup for API and UI test
- Setting up Parallel execution
- Adopt BDD approach to eliminate need of separate Manual Test Script
- Emailable Execution Report from CI/CD Pipeline
- Allure or Other simialr Report
- Scenario Tagging  e.g. @Smoke
- Visual Regression (using cypress-image-diff library)

### Note: 
There were several instances of [GoRest](https://gorest.co.in/) not loading properly. Kindly check if the site works before executing API tests or in case of failure.
