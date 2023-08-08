package gr.aueb.cf.homework.lotto6App;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  The {@link Lotto6App} class reads integers from a file
 *  until it finds sentinel -1.
 *  The file must contain more than 6 integers, within the 1 - 49 range (inclusive).
 *  The integers are inserted to an array and are sorted.
 *  The program produces all possible combinations of 6 integers
 *  that follow the following criteria:
 *  The combination can have up to 4 even integers
 *  The combination can have up to 4 odd integers
 *  The combination can have up to 2 consecutive integers
 *  The combination can have up to 3 integers with the same last digit
 *  The combination can have up to 3 integers within the same decade (eg. 1 - 9, 11 - 19 etc.).
 */

public class Lotto6App {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new FileInputStream("/Users/anastasiospapargyropoulos/Documents/Java/tmp/Lotto6AppInput.txt"));
             PrintStream ps = new PrintStream("/Users/anastasiospapargyropoulos/Documents/Java/tmp/Lotto6AppOutput.txt",StandardCharsets.UTF_8)) {

            final int LOTTO_SIZE = 6;
            int[] inputNumbers = new int[49];
            int[] result = new int[6];
            int pivot = 0;
            int num;
            int window;

            while ((num = in.nextInt()) != -1 && pivot <= 48) {
                if (num >= 1 && num <= 49) {
                    inputNumbers[pivot] = num;
                    pivot++;
                }
            }

            int[] numbers = Arrays.copyOfRange(inputNumbers, 0, pivot);
            Arrays.sort(numbers);

            window = pivot - LOTTO_SIZE;
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    result[0] = numbers[i];
                                    result[1] = numbers[j];
                                    result[2] = numbers[k];
                                    result[3] = numbers[l];
                                    result[4] = numbers[m];
                                    result[5] = numbers[n];

                                    if (!isEven(result, 4) && !isOdd(result, 4) && !isContiguous(result)
                                            && !isSameEnding(result, 3) && !isSameTen(result, 3)) {
                                        ps.printf("%d %d %d %d %d %d\n", result[0], result[1], result[2], result[3], result[4], result[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the number of even integers is bigger than a threshold limit.
     * @param arr           The given array.
     * @param threshold     The given threshold.
     * @return              True if the number of even integers is bigger than a threshold limit
     *                      False otherwise.
     */
    public static boolean isEven(int[] arr, int threshold) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count > threshold;
    }

    /**
     * Returns true if the number of odd integers is bigger than a threshold limit.
     * @param arr           The given array.
     * @param threshold     The given threshold.
     * @return              True if the number of odd integers is bigger than a threshold limit
     *                      False otherwise.
     */
    public static boolean isOdd(int[] arr, int threshold) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 != 0) {
                count++;
            }
        }
        return count > threshold;
    }

    /**
     * Returns true if the number of consecutive integers is greater than 2.
     * @param arr           The given array.
     * @return              True if the number of consecutive integers is greater than 2
     *                      False otherwise.
     */
    public static boolean isContiguous(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            if ((Math.abs(arr[i] - arr[i+1]) == 1) && Math.abs(arr[i+1] - arr[i+2]) == 1) {
                count++;
            }
        }
        return count > 0;
    }

    /**
     * Returns true if the number of integers with the same last digit is bigger than a threshold limit.
     * @param arr         The given array.
     * @param threshold   The given threshold.
     * @return            True if the number of integers with the same last digit is bigger than a threshold limit
     *                    False otherwise.
     */
    public static boolean isSameEnding(int[] arr, int threshold) {
        boolean isMore = false;
        int[] helperArr = new int[10];
        for (int i : arr) {
            helperArr[i % 10]++;
            }
        for (int num : helperArr) {
            if (num > threshold) {
                isMore = true;
                break;
            }
        }
        return isMore;
    }

    /**
     * Returns true if the number of integers within the same decade (1-9, 10-19, etc) is bigger than a threshold limit.
     * @param arr         The given array.
     * @param threshold   The given threshold.
     * @return            True if the number of integers within the same decade is bigger than a threshold limit
     *                    False otherwise.
     */
    public static boolean isSameTen(int[] arr, int threshold) {
        boolean isMore = false;
        int[] helperArr = new int[5];
        for (int i : arr) {
            helperArr[i / 10]++;
        }
        for (int num : helperArr) {
            if (num > threshold) {
                isMore = true;
                break;
            }
        }
        return isMore;
    }
}
