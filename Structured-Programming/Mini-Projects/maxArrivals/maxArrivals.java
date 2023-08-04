package gr.aueb.cf.homework.myJavaProjects.maxArrivals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The {@link maxArrivals} class
 *  calculates the maximum number of cars parked at the same time in a garage.
 *  The data is provided in a 2D array of the following format:
 *  arr[][] = {{1012, 1136}, {1317, 1417}, {1015, 1020}}
 *  where the first car arrived at 10:12 and departed at 11:36,
 *  the second car arrived at 13:17 and departed at 14:17 etc.
 *  The app creates an additional 2D array (maxCars), that inserts in the first position
 *  the time of arrival or departure and in the second position, 1 if it's an arrival
 *  and 0 if it's a departure.
 *  By sorting the maxCars array in ascending order, it traverses it to count the concurrent cars.
 *  In the end, it prints the max count.
 */

public class maxArrivals {

    public static void main(String[] args) {

        int arr[][] = {{1012, 1136}, {1317, 1417}, {1015, 1020}};
        int [][] maxCars;

        maxCars = transformArray(arr);
        sortByTime(maxCars);

        System.out.println("Max cars in the parking place are: " + getConcurrentCars(maxCars));

    }


    /**
     * Transforms the initial array in a new array, where in one row are the arrivals
     * and the next the departures and then again arrivals and departures etc.
     * In the second column we add 1 for arrival and 0 for departure
     * @param arr   the initial array
     * @return      the transformed array
     */
    public static int[][] transformArray(int[][] arr) {
        int maxCars[][] = new int [arr.length*2][2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                maxCars[i*2][0] = arr[i][0];
                maxCars[i*2][1] = 1;
                maxCars[i*2+1][0] = arr[i][1];
                maxCars[i*2+1][1] = 0;
            }
        }
        return maxCars;
    }

    /**
     * Sorts an array by the first column, which is the time of arrival
     * or departure
     * @param arr   the given array
     */
    public static void sortByTime(int[][] arr) {
        Arrays.sort(arr, Comparator.comparing((int[] a) -> a[0]));
    }


    /**
     * Returns the number of cars that are parked at the same time
     * @param arr       The given array
     * @return          the maximum cars parked at the same time
     */
    public static int getConcurrentCars(int[][] arr) {
        int count = 0;
        int maxCount = 0;

        for (int[] ints : arr) {
            if (ints[1] == 1) {
                count++;
                if (count > maxCount) {
                    maxCount = count;
                }
            } else count --;

        }
        return maxCount;
    }
}