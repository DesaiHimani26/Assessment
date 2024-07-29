package org.example.utils;

import org.example.support.DriverFactory;
import org.example.support.PageObjectBase;

public class TestBase {

    private static TestBase instance;
    private final DriverFactory driverManager;
    private PageObjectBase pageObjectParent;

    public TestBase() {
        driverManager = new DriverFactory();
        //pageObjectParent = new PageObjectBase(driverManager.getDriver());
    }

    public static synchronized TestBase getInstance() {
        if (instance == null) {
            instance = new TestBase();
        }
        return instance;
    }
    public DriverFactory getDriverManager() {
        return driverManager;
    }

    public PageObjectBase getPageObjectParent() {
        if (pageObjectParent == null) {
            pageObjectParent = new PageObjectBase(driverManager.getDriver());
        }
        return pageObjectParent;
    }

    public void reset() {
        pageObjectParent = null;
    }

}
