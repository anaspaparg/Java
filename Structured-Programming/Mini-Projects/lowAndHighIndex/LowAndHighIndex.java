package gr.aueb.cf.homework.myJavaProjects;

/**
 * Assume a sorted array with repeated elements. Method getLowAndHighIndexOf(int [] arr, int key)
 * returns the low and high index of an array, for an integer key that gets as parameter.
 * In main() we create an array {1, 2, 4, 5, 5, 8, 8, 8, 8, 8, 10} and if for example we provide
 * as key the number 8, it should return {5, 9}.
 */

public class LowAndHighIndex {

    public static void main(String[] args) {
        int arr [] = {1, 2, 4, 5, 5, 8, 8, 8, 8, 8, 10};
        int returned [];

        returned = getLowAndHighIndexOf(arr, 8);

        System.out.printf("{%d, %d}", returned[0], returned[1]);
    }

    public static int [] getLowAndHighIndexOf(int [] arr, int key) {
        int low = 0, high = 0;
        int pivot;

        if (arr == null) return new int[] {};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                low = i;
                break;
            }
        }

        high = low;
        pivot = low + 1;

        while ((pivot < arr.length) && arr[pivot++] == key) {
            high++;
        }

        return new int[] {low, high};
     }

}
