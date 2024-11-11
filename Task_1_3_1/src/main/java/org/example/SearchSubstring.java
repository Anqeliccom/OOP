package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for finding occurrences of a substring in a file.
 */
public class SearchSubstring {

    /**
     * Finds all occurrences of a substring in a given file.
     *
     * @param filename name of the file to search in.
     * @param substring substring to search for.
     * @return list of starting indices where the substring occurs in the file.
     */
    public static List<Integer> findSubstringOccurrences(String filename, String substring) {
        List<Integer> occurrences = new ArrayList<>();

        if (substring.isEmpty()) {
            return occurrences;
        }

        int[] pattern = substring.codePoints().toArray();
        int patternLength = pattern.length;

        try (Reader reader = new InputStreamReader
                (new FileInputStream(filename), StandardCharsets.UTF_8)) {
            int[] buffer = new int[patternLength];
            int bufferIndex = 0;
            int index = 0;
            int codePoint;

            while ((codePoint = reader.read()) != -1) {
                buffer[bufferIndex % patternLength] = codePoint;

                if (index >= patternLength - 1) {
                    if (matchesPattern(buffer, bufferIndex % patternLength, pattern)) {
                        occurrences.add(index - patternLength + 1);
                    }
                }
                bufferIndex++;
                index++;
            }
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }
        return occurrences;
    }

    /**
     * Compares a buffer of characters with the given substring's pattern.
     *
     * @param buffer buffer of characters to compare.
     * @param startIndex starting index within the buffer to compare from.
     * @param pattern pattern of the substring represented as an array of code points.
     * @return true if the buffer matches the pattern, false otherwise.
     */
    private static boolean matchesPattern(int[] buffer, int startIndex, int[] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            if (buffer[(startIndex + i + 1) % pattern.length] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
