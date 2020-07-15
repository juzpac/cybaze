package com.mvvmrxjavatemp.utils;

import java.io.IOException;

public class NoInternetException extends IOException {
    @Override
    public String getMessage() {
        return "No network available, Please check your WiFi or Data connection.";
    }
}