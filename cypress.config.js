const { defineConfig } = require("cypress");
require('dotenv').config();

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    charts: true,
    reportPageTitle: 'Assessment',
    embeddedScreenshots: true,
    inlineAssets: true,
    saveAllAttempts: false,
  },
  e2e: {
    setupNodeEvents(on, config) {
        require('cypress-mochawesome-reporter/plugin')(on);
        config.env.ACCESS_TOKEN = process.env.ACCESS_TOKEN; // Load ACCESS_TOKEN from .env
        config.env.apiBaseURL = 'https://gorest.co.in'; // Set base URL for API Tests
      return config; // Return the updated config
      },
    specPattern: "cypress/e2e/*/*.*",
    defaultCommandTimeout: 10000,
    chromeWebSecurity: false,
    watchForFileChanges: false,
    video:true,
  }
});