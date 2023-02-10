package com.adruy.algo;

import java.util.Arrays;

public class ReverseWord {

    public static void main(String[] args) {
        System.out.println(reverseWords("My name is Aditya"));
    }

    public static String reverseWords(String input) {
        var result = new StringBuilder();

        if(input == null || input.isEmpty()) {
            return result.toString();
        }

        final String delimiter = " ";
        var words = input.split(delimiter);

        for(int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }
        return result.toString().trim();
    }
}