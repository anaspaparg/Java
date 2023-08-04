package gr.aueb.cf.homework.myJavaProjects.armstrongNumbers;

import java.util.Scanner;

/**
 * Finds if the sum of the power of every digit, in the power
 * of the count of digits, is equal with the number itself.
 * For example 153 = 1^3 + 5^3 + 3^3
 */

public class ArmstrongNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please provide a number");
        int num = in.nextInt();
        int count = 0;
        int result = 0;

        count = getCount(num);
        result = getResult(num, count);
        System.out.println("The given number is Armstrong number: " + isArmstrong(num, result));
    }

    /**
     * Finds the count of the digits of a number
     * @param num   the given number
     * @return      the count
     */
    public static int getCount(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num = num / 10;
        }
        return count;
    }

    /**
     * Calculates the power of every digit in the power of the count
     * @param num       the given number
     * @param count     count of digits
     * @return          the result of the calculation
     */
    public static int getResult(int num, int count) {
        int tmp = 0;
        int result = 0;
        while (num > 0) {
            tmp = num % 10;
            result = result + (int) Math.pow(tmp, count);
            num = num / 10;
        }
        return result;
    }

    /**
     * Chackes if a number is an Armstrong number
     * @param num       the given number
     * @param result    the result of the calculation from the above method
     * @return          true if number is Armstrong
     *                  false otherwise
     */
    public static boolean isArmstrong(int num, int result) {
        return num == result;
    }
}
