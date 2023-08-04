package gr.aueb.cf.homework.myJavaProjects.CharReadAndStatisticsApp;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *  The {@link CharReadAndStatisticsApp} class
 *  reads one-by-one, all characters(any UTF-8 character) of a file and
 *  puts them in a 2D array, 256x2, in which the first position is for the
 *  character and the second for the count of the character.
 *  main() prints out statistics for each character,
 *  sorted by character and
 *  by count.
 */

public class CharReadAndStatisticsApp {
    final static Path path = Paths.get("\"/Users/anastasiospapargyropoulos/IdeaProjects/java-jr/src/gr/aueb/cf/homework/myJavaProjects/CharReadAndStatisticsApp/log.txt");
    final static int[][] text = new int[256][2];
    static int pivot = -1;
    static int count = 0;


    public static void main(String[] args) throws IOException {
        try {
            readTextAndSave();
            printStatistics();
        } catch (IOException e) {
            System.out.println("Error in input/output");
        }

    }


    /**
     * Reads text from a file using a FileInputStream with 4096 bytes buffering.
     * Each character is saved in the 2D array.
     * @throws IOException                  if file name is invalid
     * @throws IllegalArgumentException     if 2D array is full or any other storage error
     */
    public static void readTextAndSave() throws IOException, IllegalArgumentException {
        int ch;
        byte[] buffer = new byte[4096];
        int next = 0;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/Users/anastasiospapargyropoulos/IdeaProjects/java-jr/src/gr/aueb/cf/homework/myJavaProjects/CharReadAndStatisticsApp/grades.txt"))) {
            while ((next = bis.read(buffer)) > 0) {
                for (int i = 0; i < next; i++) {
                    if (!saveChar(buffer[i])) {
                        throw new IllegalArgumentException("Error in save");
                    } else {
                        count++;
                    }
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }


    /**
     * For each character in the text, prints the count and the percentage, sorted by
     * character ascending and percentage ascending
     */
    public static void printStatistics() {
        int[][] finalText = Arrays.copyOfRange(text, 0, pivot + 1);

        Arrays.sort(finalText, Comparator.comparingInt(a -> a[0]));
        System.out.println("Statistics (Character ascending)");
        for (int[] element : finalText) {
            System.out.printf("The character '%c' is found %d times, with a percentage of %.2f%%\n", element[0], element[1], (element[1] / (double) count));
        }

        Arrays.sort(finalText, Comparator.comparingInt(a -> a[1]));
        System.out.println("Statistics (Percentage ascending)");
        for (int[] element : finalText) {
            System.out.printf("The character '%c' is found %d times, with a percentage of %.2f%%\n", element[0], element[1], (element[1] / (double) count));
        }
    }


    /**
     * Inserts a character in a 2D array with its occurrence's count in the text
     * @param ch        The character
     * @return          true, if character is inserted in the array
     *                  or false otherwise
     */
    public static boolean saveChar(int ch) {
        int charPosition = -1;
        boolean inserted = false;

        if (isFull(text)) {
            return false;
        }

        charPosition = getCharPosition(ch);

        if (charPosition == -1) {
            pivot++;
            text[pivot][0] = ch;
            text[pivot][1] += 1;
            inserted = true;
        } else {
            text[charPosition][1]++;
            inserted = true;
        }
        return inserted;
    }

    /**
     * Gets the position of the character in the array
     * @param ch    an integer, representing the character
     * @return      if character exists, returns index,
     *              -1 otherwise
     */
    public static int getCharPosition(int ch) {
        for (int i = 0; i <= pivot; i++) {
            if (text[i][0] == ch) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Checks if array is full
     * @param text      the array of characters
     * @return          true if array is full,
     *                  false otherwise
     */
    public static boolean isFull(int[][] text) {
        return pivot == text.length - 1;
    }


    /**
     * Logs the errors
     */
    public static void log(Exception e) throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
