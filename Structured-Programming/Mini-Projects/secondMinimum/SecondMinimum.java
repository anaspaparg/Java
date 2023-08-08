package gr.aueb.cf.homework.myJavaProjects;

/**
 * Returns the second minimum value, from an array of integers
 */

public class SecondMinimum {
    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 48, 2, 3};

        int secMinVal;

        secMinVal = getSecMinValue(arr);
        System.out.println(secMinVal);
    }


    public static int getSecMinValue(int[] arr) {
        int minValue = 0;
        int secMinVal = 1;
        int min = 0;
        int secMin = 1;

        if (arr == null) return -1;
        if (arr.length <= 2) return -1;


        minValue = arr[0];
        secMinVal = arr[1];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue && arr[i] < secMinVal) {
                secMinVal = minValue;
                minValue = arr[i];
                secMin = min;
                min = i;
            } else if (arr[i] > minValue && arr[i] < secMinVal) {
                secMinVal = arr[i];
                secMin = i;
            }
        }
        return secMinVal;
    }

}

