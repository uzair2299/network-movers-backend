package com.company.networkmovers.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.security.SecureRandom;

public class CodeGeneratorUtil {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    /**
     * Generates a unique 8-digit code.
     * Format: YYMMDD + 2 random digits (e.g., 26061147)
     */
    public static String generate8DigitCode() {
        String datePart = LocalDateTime.now().format(DATE_FORMATTER);
        int randomPart = RANDOM.nextInt(100); // 0 to 99
        return datePart + String.format("%02d", randomPart);
    }
}
