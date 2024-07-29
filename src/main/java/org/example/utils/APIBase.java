package org.example.utils;

import org.example.pages.apiPage.BoardRequests;

public class APIBase {

    private BoardRequests boardRequests;

    public BoardRequests getboardRequests() {
        return (boardRequests == null) ? boardRequests = new BoardRequests() : boardRequests;
    }
}
