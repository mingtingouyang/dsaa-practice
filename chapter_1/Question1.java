package chapter_1;

import java.util.Arrays;
import java.util.Random;

public class Question1 {
    
    /**
     * Solution 1
     * Using quick sort to sort all numbers and directly find the Kth larger number.
     * @param nums
     * @param k
     * @return
     */
    private static int findKthLargeNumber1(Integer[] nums, int k) {
        Arrays.sort(nums); // Use quick sort.
        return nums[nums.length - k];
    } 

    /**
     * Solution 2
     * First add the first K numbers into a new array which length is k, and sort them.
     * Second add the rest numbers into the new array using binary search to find its place one by one.
     * Finally return the first number in the new array.
     * @param nums
     * @param k
     * @return
     */
    //Solution 2
    private static int findKthLargeNumber2(Integer[] nums, int k) {
        // First sort the first k numbers.
        Integer[] window = Arrays.copyOf(nums, k);
        Arrays.sort(window);

        // Second add the rest numbers into the new array one by one.
        for (int i = k; i < nums.length; i++) {
            int index = binarySearch(window, nums[i]);
            if (index >= 0) {
                for (int j = 1; j <= index; j++) {
                    window[j - 1] = window[j];
                }
                window[index] = nums[i];
            }
        }

        return window[0];
    }
    /**
     * return the index of the first number smaller than target or equal to target in array. array is sorted by ascending order.
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearch(Integer[] nums, int target) {
        if(target < nums[0]) return -1; //can't find
        if(target > nums[nums.length - 1]) return nums.length - 1;

        int left = 0;
        int right = nums.length - 1;
        
        while(right > left + 1) {
            int mid = (left + right) >>> 1;
            
            if(nums[mid] > target)
                right = mid;
            else if(nums[mid] < target)
                left = mid;
            else
                return mid;
        }

        return left;
    }

    /**
     * Solution 3
     * 
     * @param nums
     * @param k
     * @return
     */
    private static int findKthLargeNumber3(Integer[] nums, int k) {
        return 0;
    }

    public static void main(String[] args) {
        int N = 100000;
        for (int i = 5000; i < N + 1; i+=5000) {
            System.out.println(String.format("*** Test for N = %d***", i));
            runTest(i);
        }
    }

    private static void runTest(int N){
        // build test data set
        Random random = new Random(1000);
        Integer[] dataSet = new Integer[N];
        for (int i = 0; i < N; i++) {
            dataSet[i] = random.nextInt(100000);
        }

        long start = System.currentTimeMillis();
        int result = findKthLargeNumber1(dataSet, N >>> 1);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Solution 1 >> results: %d, time: %dms.", result, end - start));

        start = System.currentTimeMillis();
        result = findKthLargeNumber2(dataSet, N >>> 1);
        end = System.currentTimeMillis();
        System.out.println(String.format("Solution 2 >> results: %d, time: %dms.", result, end - start));

        start = System.currentTimeMillis();
        result = findKthLargeNumber3(dataSet, N >>> 1);
        end = System.currentTimeMillis();
        System.out.println(String.format("Solution 3 >> results: %d, time: %lms.", result, end - start));
    }

}