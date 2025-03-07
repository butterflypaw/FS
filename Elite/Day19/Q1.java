// AlphaCipher is a string formed from another string by rearranging its letters

// You are given a string S.
// Your task is to check, can any one of the AlphaCipher is a palindrome or not.

// Input Format:
// -------------
// A string S

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// carrace

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// code

// Sample Output-2:
// ----------------
// false


import java.util.*;
public class Q1{
    static boolean canBePalindrome(String s){
        int xor = 0;
        for(char c : s.toCharArray()){
            xor ^= c;
        }
        if(xor == 0) return true;
        for(char c : s.toCharArray()){
            if((char)xor == c) return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(canBePalindrome(s));
    }
}