package com.sosadwaden.pasteboxtesttask.utils;

import com.sosadwaden.pasteboxtesttask.exception.Base62Exception;

/**
 * Класс для преобразования из/в 62 СС.
 */
public class Base62 {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 62;

    public static String encode(int input) {

        if (input < 0) {
            String message = String.format("Input must not be negative, have '%d'", input);
            throw new Base62Exception(message);
        }

        if (input == 0) {
            return "a";
        }

        StringBuilder encodedString = new StringBuilder();

        while (input != 0) {
            encodedString.append(ALPHABET.charAt(input % BASE));
            input /= BASE;
        }

        return encodedString.reverse().toString();
    }

    public static int decode(String input) {
        int decoded = 0;

        for (char ch: input.toCharArray()) {

            if (ch == ' ') {
                continue;
            }

            decoded *= BASE;
            int current = ALPHABET.indexOf(ch);

            if (current == -1) {
                String message = String.format("string '%s' is not a base62 number", input);
                throw new Base62Exception(message);
            }
            decoded += current;

        }

        return decoded;
    }

}
