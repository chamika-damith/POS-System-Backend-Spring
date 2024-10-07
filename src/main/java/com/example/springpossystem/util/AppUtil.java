package com.example.springpossystem.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCusId() {
        return "CUS-"+ UUID.randomUUID();
    }

    public static String generateItemId() {
        return "I-"+ UUID.randomUUID();
    }

    public static String generateOrderId() {
        return "Or-"+ UUID.randomUUID();
    }

    public static String generateOrderDetailId() {
        return "OD-"+ UUID.randomUUID();
    }
}
