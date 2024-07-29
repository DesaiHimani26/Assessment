# Technical Assessment - Knab

### Deliverables
- [Test Cases](https://github.com/DesaiHimani26/Assessment/blob/master/Test%20Cases.xlsx)
- [Test Plan](https://github.com/DesaiHimani26/Assessment/blob/master/Test%20Plan.docx)
- [Automation Tests](https://github.com/DesaiHimani26/Assessment/tree/master/src/test/resources/features)
- [Test Report](https://github.com/DesaiHimani26/Assessment/blob/master/sample-test-report.html) 

### Techincal Details:
1. Language : [Java 8](https://www.java.com/en/download/help/java8.html)
2. Build Tool and Dependencies Management : [Maven](https://maven.apache.org/what-is-maven.html)
3. Test Automation Tool: [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/)
4. Framework : [BDD Cucumber](https://cucumber.io/docs/guides/overview/)
5. Reporting Framework : [Surefire Cucumber Report](https://maven.apache.org/surefire/maven-surefire-plugin/examples/cucumber.html)


### Required installations
1. Java 8 + Maven
2. IDE : IntelliJ IDEA Community Edition (or you can use IDE of your choice like Eclipse)


### Getting Started & necessary installation
1. Ensure installation of above is completed
2. Clone this repository or download the zip with the source code in to your local system.
```
git clone https://github.com/DesaiHimani/technical-qa-assignment
```

### Test Execution
To Run tests from command line
- Open command terminal 
- Go to path of your project where you have cloned above repo
  
```
$ cd Assessment

$ mvn clean install

```

### Test report generation
Upon execution, test reports in different formats will be generated under _target_ folder with name 'cucumber-reports.html'

### Framework Overview

| Folder  | Significance |  
|----------|----------|
| src/main/java/org/example/constants | This contains constants for driver and API Request | 
| src/main/java/org/example/pages | This contains page class for api and UI pages |
| src/main/java/org/example/support | This contains classes for driver setup, file reading, config reader, requet builder etc. |
| src/main/java/org/example/utils | This contains classes for utils and custom selenium commands, including base classes |
| src/test/java/org/example/runner | This contains main Runner class |
| src/test/java/org/example/stepDefinitions | This contains step definition class for respective faeture file |
| src/test/resources/features | This contains feature file for different Tests |
|config.properties | This file contains configuration parameters to drive UI and API tests|


