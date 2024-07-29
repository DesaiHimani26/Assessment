package org.example.support;

import org.example.constants.DriverTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    private final Properties properties;

    private String apiBaseUrl;

    private String apiToken;

    private String apiKey;

    public ReadConfig() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String propertyFilePath = "config.properties";

        try {
            fileReader = new FileReader(propertyFilePath);
            bufferedReader = new BufferedReader(fileReader);
            properties = new Properties();

            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getUrl() {
        String url = properties.getProperty("url");

        if (url != null) return url;
        else
            throw new RuntimeException("url not specified in the config file.");
    }

    public long getTime() {
        String timeout = properties.getProperty("timeout");

        if (timeout != null) {
            return Long.parseLong(timeout);
        } else {
            throw new RuntimeException("timeout not specified in the config file.");
        }
    }

    public DriverTypes getBrowser()  {
        String browserName = properties.getProperty("browser");

        switch (browserName) {
            case "chrome":
                return DriverTypes.CHROME;
            case "firefox":
                return DriverTypes.FIREFOX;
            case "edge":
                return DriverTypes.EDGE;
            case "safari":
                return DriverTypes.SAFARI;
            default:
                throw new RuntimeException("Browser name key value in config file does not match: " + browserName);
        }
    }

    public String getAPIKey() {
         apiKey = properties.getProperty("api_key");

        if (apiKey != null) {
            return apiKey;
        } else {
            throw new RuntimeException("API Key is not specified in the config file. Please check.");
        }
    }

    public String getAPIToken() {
         apiToken = properties.getProperty("api_token");

        if (apiToken != null) {
            return apiToken;
        } else {
            throw new RuntimeException("API Token is not specified in the config file. Please check.");
        }
    }

    public String getAPIBaseUrl() {
         apiBaseUrl = properties.getProperty("api_base_url");

        if (apiBaseUrl != null) {
            return apiBaseUrl;
        } else {
            throw new RuntimeException("API Base URL is not specified in the config file. Please check.");
        }
    }


}
