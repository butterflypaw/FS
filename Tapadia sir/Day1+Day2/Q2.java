// You are given an array consisting of N integers, and an integer, K. 
// Your task is to determine the minimum element in subarrays of size K.

// Sample Input1:
// --------------
// 5
// 10 12 14 11 15
// 3

// Sample Output1:
// ---------------
// 10 11 11

// Sample Input2:
// --------------
// 5
// 5 2 1 1 1
// 4

// Sample Output2:
// ---------------
// 1 1

//Code for maximum number:
import java.util.*;
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        int l = 0, r = 0, i = 0;
        while (r < n) {
            while (!q.isEmpty() && q.peekLast() < nums[r]) q.pollLast();
            q.addLast(nums[r]);
            if (r - l + 1 == k) {
                arr[i] = q.peekFirst();
                if (nums[l] == q.peekFirst()) q.pollFirst();
                i++;
                l++;
            }
            r++;
        }
        return arr;
    }
}
