package com.example;

import java.util.Set;

public class CountVowels {

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public static boolean isVowel(char c) {
        return VOWELS.contains(Character.toLowerCase(c));
    }

    public static int countVowels(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (isVowel(c)) count++;
        }
        return count;
    }
}
