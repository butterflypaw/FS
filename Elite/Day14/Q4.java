// Two brothers want to play a game, 
// The rules of the game are: one player gives two sorted lists of 
// numerical elements and a number (sum). 
// The opponent has to find the closest pair of elements 
// to the given sum.
// -> pair consists of elements from each list

// Please help those brothers to develop a program, that takes 
// two sorted lists as input and return a pair as output.

// Input Format:
// -------------
// size of list_1
// list_1 values
// size of list_2
// list_2 values
// closest number

// Output Format:
// --------------
// comma-separated pair

// Sample Input-1:
// ---------------
// 4
// 1 4 5 7
// 4
// 10 20 30 40
// 32
// Sample Output-1
// ---------------
// 1, 30

// Sample Input-2
// ---------------
// 3
// 2 4 6
// 4
// 5 7 11 13
// 15
// sample output-2
// ---------------
// 2, 13

import java.util.*;
public class Q4{
    static void getPairs(int n, int[] arr1, int m, int[] arr2, List<Integer> ans, int k){
        int diff = Integer.MAX_VALUE;
        int a = 0, b = 0, i = 0;
        while(i < n){
            int temp = binarySearch(m, arr2, k-arr1[i]);
            if(Math.abs((temp+arr1[i])-k) < diff){
                diff = Math.abs((temp+arr1[i])-k);
                a = arr1[i];
                b = temp;
            }
            i++;
        }
        ans.add(a);
        ans.add(b);
    }
    static int binarySearch(int m, int[] arr, int num){
        int l = 0, r = m-1;
        int b = 0;
        int diff = Integer.MAX_VALUE;
        while(l <= r){
            int mid = (l+r)/2;
            if(diff > Math.abs(arr[mid]-num)){
                diff = Math.abs(arr[mid]-num);
                b = arr[mid];
            }
            if(arr[mid] == num){
                b = arr[mid];
                break;
            }
            if(arr[mid] > num) r = mid-1;
            if(arr[mid] < num) l = mid + 1;
        }
        return b;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[]arr1 = new int[n];
        for(int i = 0; i < n; i++) arr1[i] = sc.nextInt();
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for(int i = 0; i < m; i++) arr2[i] = sc.nextInt();
        int k = sc.nextInt();
        List<Integer> ans = new ArrayList<>();
        getPairs(n, arr1, m, arr2, ans, k);
        System.out.println(ans);
        sc.close();
    }
}