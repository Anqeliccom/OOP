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

        int[] psa = buildPrefixSuffixArray(pattern);

        try (Reader reader = new InputStreamReader(
            new FileInputStream(filename), StandardCharsets.UTF_8)) {
            int fileIndex = 0;
            int patternIndex = 0;
            int codePoint;

            while ((codePoint = reader.read()) != -1) {
                if (codePoint == pattern[patternIndex]) {
                    patternIndex++;
                    if (patternIndex == patternLength) {
                        occurrences.add(fileIndex - patternLength + 1);
                        patternIndex = psa[patternIndex - 1];
                    }
                } else {
                    if (patternIndex != 0) {
                        patternIndex = psa[patternIndex - 1];
                        continue;
                    }
                }
                fileIndex++;
            }
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }
        return occurrences;
    }

    /**
     * Builds the prefix-suffix array.
     * This array stores the length of the longest prefix of the pattern
     * that is also a suffix for each position in the pattern.
     *
     * @param pattern array of code points.
     * @return prefix-suffix array for the given pattern.
     */
    private static int[] buildPrefixSuffixArray(int[] pattern) {
        int[] psa = new int[pattern.length];
        int length = 0;
        int i = 1;

        while (i < pattern.length) {
            if (pattern[i] == pattern[length]) {
                length++;
                psa[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = psa[length - 1];
                } else {
                    psa[i] = 0;
                    i++;
                }
            }
        }
        return psa;
    }
}