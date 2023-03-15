package com.restauapp.badcontentcomponent;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BadContentDetector {

    private static final List<String> BAD_WORDS = Arrays.asList("con", "pute", "merde");
    private static final String REPLACEMENT_STRING = "[Contenu indésirable]";

    public String detectBadContent(String input) {
        String[] words = input.split("\\s+");
        StringBuilder output = new StringBuilder();

        for (String word : words) {
            if (BAD_WORDS.contains(word)) {
                output.append(REPLACEMENT_STRING);
            } else {
                output.append(word);
            }
            output.append(" ");
        }

        return output.toString().trim();
    }
}