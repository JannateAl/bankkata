package com.bankkata.utils;

import java.time.LocalDateTime;

public class LoggerUtil {
    
    public static void logInfo(String message) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + message);
    }

    public static void logError(String message) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + message);
    }
}
