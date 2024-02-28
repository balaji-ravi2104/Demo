package com.banking.utils;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]|,./?><";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword() {
        return generatePassword(8);
    }

    private static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        String allowedChars = PASSWORD_ALLOW_BASE;
        for (int i = 0; i < length; i++) {
            int randomCharIndex = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomCharIndex));
        }
        return password.toString();
    }
}
