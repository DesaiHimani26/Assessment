package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtil {
    private static ExtentReports extentReports;
    private static ExtentTest test;
   // private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initialize() {
        if (extentReports == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/extent-report.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
        }
    }

    public static ExtentReports getExtent() {
        return extentReports;
    }

    public static void setTest(ExtentTest testInstance) {
        test = testInstance;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}