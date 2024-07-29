package org.example.support;

import org.example.pages.uiPages.BoardPage;
import org.example.pages.uiPages.LandingPage;
import org.example.pages.uiPages.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectBase {
    private  final WebDriver webDriver;
    private LoginPage loginPage;
    private LandingPage landingPage;
    private BoardPage boardPage;



    public PageObjectBase(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LandingPage getHomePage() {
        return (landingPage == null) ? landingPage = new LandingPage(webDriver) : landingPage;
    }

    public LoginPage getLoginPage() {

        return (loginPage == null) ? loginPage = new LoginPage(webDriver) : loginPage;
    }

    public BoardPage getBoardPage() {

        return (boardPage == null) ? boardPage = new BoardPage(webDriver) : boardPage;
    }

}
