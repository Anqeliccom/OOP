package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the SearchSubstring class.
 */
class SearchSubstringTest {

    private static final String TEST_FILE = "test_file.txt";

    @AfterEach
    void deleteTestFile() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Generates a large file with a pattern embedded between blocks of data.
     *
     * @param sizeBeforeBytes number of bytes to generate before the pattern.
     * @param pattern         pattern to insert.
     * @param sizeAfterBytes  number of bytes to generate after the pattern.
     * @throws IOException    if an I/O error occurs.
     */
    private static void generateLargeFileWithPattern(
        long sizeBeforeBytes, String pattern, long sizeAfterBytes) throws IOException {
        try (BufferedOutputStream fileOutput = new BufferedOutputStream(
            new FileOutputStream(SearchSubstringTest.TEST_FILE))) {
            byte[] buffer = new byte[1024 * 1024];
            Arrays.fill(buffer, (byte) '0');

            writeRepeatedBytes(fileOutput, buffer, sizeBeforeBytes);
            fileOutput.write(pattern.getBytes(StandardCharsets.UTF_8));
            writeRepeatedBytes(fileOutput, buffer, sizeAfterBytes);
        }
    }

    private static void writeRepeatedBytes(
        BufferedOutputStream fileOutput, byte[] buffer, long totalBytes) throws IOException {
        for (long written = 0; written < totalBytes; written += buffer.length) {
            int toWrite = (int) Math.min(buffer.length, totalBytes - written);
            fileOutput.write(buffer, 0, toWrite);
        }
    }

    private void writeToFile(String content) throws IOException {
        Files.writeString(Paths.get(TEST_FILE), content, StandardCharsets.UTF_8);
    }

    @Test
    void testSmallFile() throws IOException {
        writeToFile("abcabcabc");
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(TEST_FILE, "abc");
        assertEquals(List.of(0, 3, 6), occurrences);
    }

    @Test
    void testEmptyFile() throws IOException {
        writeToFile("");
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(TEST_FILE, "test");
        assertEquals(0, occurrences.size());
    }

    @Test
    void testSubstringLongerThanFile() throws IOException {
        writeToFile("abc");
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(TEST_FILE, "abcdef");
        assertEquals(0, occurrences.size());
    }

    @Test
    void testFullMatchFile() throws IOException {
        writeToFile("meaningfulness");
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(
            TEST_FILE, "meaningfulness");
        assertEquals(List.of(0), occurrences);
    }

    @Test
    void testNoMatchFile() throws IOException {
        writeToFile("some text");
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(TEST_FILE, "string");
        assertEquals(0, occurrences.size());
    }

    @Test
    void testLimitedHeapMemory() throws IOException {
        long sizeBefore = 512L * 1024 * 1024;
        long sizeAfter = 512L * 1024 * 1024;
        String pattern = "_pattern_";

        generateLargeFileWithPattern(sizeBefore, pattern, sizeAfter);
        int expectedPosition = (int) sizeBefore;
        List<Integer> occurrences = SearchSubstring.findSubstringOccurrences(TEST_FILE, pattern);
        assertEquals(Collections.singletonList(expectedPosition), occurrences);

        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Max memory(bytes) used during the test: " + maxMemory);
        assertTrue(maxMemory <= 256 * 1024 * 1024);
    }
}