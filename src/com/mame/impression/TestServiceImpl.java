package com.mame.impression;

public class TestServiceImpl  implements TestService {
    @Override public String hello(String str) {
        return "hello. " + str;
    }

}
