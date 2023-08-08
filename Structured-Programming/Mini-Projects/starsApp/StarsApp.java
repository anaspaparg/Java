package gr.aueb.cf.homework.myJavaProjects;

/**
 * Displays a menu with 6 options and after user provides an input, it displays stars with different layout
 */

import java.util.Scanner;

public class StarsApp {
    static int choice = 0;
    static int n;
    static int result;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        do {
            printMenu();
            choice = in.nextInt();

            if (isChoiceInvalid(choice)) {
                System.out.println("Wrong choice");
                continue;
            }

            if (isChoiceQuit(choice)) {
                break;
            }

            System.out.println("How many stars would you like to display?");
            n = in.nextInt();

            onChoiceGetResult(choice);
            break;

        } while(true);
    }

    /**
     * Prints a menu
     */
    public static void printMenu() {
        System.out.println("1. Display stars horizontally");
        System.out.println("2. Display stars vertically");
        System.out.println("3. Display n rows with n stars");
        System.out.println("4. Display n rows with stars 1 – n");
        System.out.println("5. Display n rows με stars n – 1");
        System.out.println("6. Exit");
        System.out.println("Choose one of the above choices");
    }

    public static boolean isChoiceInvalid(int choice) {
        return (choice < 1 || choice > 6);
    }

    public static boolean isChoiceQuit(int choice) {
        return (choice == 6);
    }

    /**
     * Prints starts horizontally
     * @param n number of stars to print
     */
    public static void starsH(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print("*");
        }
    }

    /**
     * Prints starts vertically
     * @param n number of stars to print
     */
    public static void starsV(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println("*");
        }
    }

    /**
     * Prints stars horizontally and vertically
     * @param n number of stars to print
     */
    public static void starsVH(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Prints stars horizontally and vertically in format 1-n
     * @param n number of stars to print
     */
    public static void starsVH1n(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Prints stars horizontally and vertically in format n-1
     * @param n number of stars to print
     */
    public static void starsVn1(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Based on what the choice is, it selects a method to use
     * @param choice
     * @return
     */
    public static int onChoiceGetResult(int choice) {

        switch (choice) {
            case 1:
                starsH(n);
                break;
            case 2:
                starsV(n);
                break;
            case 3:
                starsVH(n);
                break;
            case 4:
                starsVH1n(n);
                break;
            case 5:
                starsVn1(n);
                break;
            default:
                System.out.println("Choose between 1-6");
        }
        return result;
    }

}
