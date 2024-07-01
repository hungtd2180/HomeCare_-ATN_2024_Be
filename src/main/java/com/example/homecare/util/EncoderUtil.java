package com.example.homecare.util;

import java.util.Base64;
import java.util.UUID;

public class EncoderUtil {
    public static String createId(String field){
        return field + UUID.randomUUID().toString();
    }
    public static String encodeBase64Password(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
