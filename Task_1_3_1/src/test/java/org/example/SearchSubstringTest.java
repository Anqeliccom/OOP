package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for the SearchSubstring class.
 */
class SearchSubstringTest {

    /**
     * Generates a file containing a repeated string.
     *
     * @param filename name of the file to create.
     * @param content content to repeat in the file.
     * @param repeatCount number of times the content should be repeated.
     * @throws IOException if an I/O error occurs during file writing.
     */
    private static void generateRepeatedStringFile
    (String filename, String content, int repeatCount) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < repeatCount; i++) {
                writer.write(content);
            }
        }
    }

    @Test
    void testSmallFile() throws IOException {
        String filename = "small_file.txt";
        String content = "abcabcabc";
        generateRepeatedStringFile(filename, content, 1);

        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(filename, "abc");
        assertEquals(List.of(0, 3, 6), occurrences);
    }

    @Test
    void testLargeFile() throws IOException {
        String filename = "large_file.txt";
        int repeatCount = 1000000;
        String substring = "abcd";

        generateRepeatedStringFile(filename, substring, repeatCount);

        long startTime = System.nanoTime();
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(filename, substring);
        long endTime = System.nanoTime();

        System.out.println("Time taken for search: " + (endTime - startTime) / 1000000 + " ms");
        assertEquals(repeatCount, occurrences.size());
    }

    @Test
    void testEmptyFile() throws IOException {
        String filename = "empty_file.txt";
        generateRepeatedStringFile(filename, "test", 0);

        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(filename, "test");
        assertEquals(0, occurrences.size());
    }

    @Test
    void testSubstringLongerThanFile() throws IOException {
        String filename = "short_file.txt";
        generateRepeatedStringFile(filename, "abc", 1);

        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(filename, "abcdef");
        assertEquals(0, occurrences.size());
    }

    @Test
    void testFullMatchFile() throws IOException {
        String filename = "full_match_file.txt";
        String content = "meaningfulness";
        generateRepeatedStringFile(filename, content, 1);

        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(filename, content);
        assertEquals(List.of(0), occurrences);
    }

    @Test
    void testNoMatchFile() throws IOException {
        String filename = "no_match_file.txt";
        generateRepeatedStringFile(filename, "some text", 1);

        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(filename, "string");
        assertEquals(0, occurrences.size());
    }
}
