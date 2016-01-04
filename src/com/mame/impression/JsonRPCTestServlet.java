package com.mame.impression;

import javax.servlet.ServletException;

import org.jabsorb.JSONRPCBridge;
import org.jabsorb.JSONRPCServlet;

public class JsonRPCTestServlet extends JSONRPCServlet {

    @Override public void init() throws ServletException {
        super.init();
        JSONRPCBridge.getGlobalBridge().registerObject("testService", new TestServiceImpl());
    }

}
