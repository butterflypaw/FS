// Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

// I	f(I)
// -------
// 0	""
// 1	"0"
// 2	"1"
// 3	"00"
// 4	"01"
// 5	"10"
// 6	"11"
// 7	"000"

// You are given an integer value I, where I is positive number.
// Your task is to find BBC representation of  the given number I.

// Input Format:
// -------------
// An integer I

// Output Format:
// --------------
// Print the BBC representation of I.


// Sample Input-1:
// ---------------
// 23

// Sample Output-1:
// ----------------
// 1000


// Sample Input-2:
// ---------------
// 45

// Sample Output-2:
// ----------------
// 01110

import java.util.*;
public class Q3{
    static String getString(int n){
        if(n == 0) return "";
        int len = 1, group = 1;
        while(group*2 - 1 <= n){
            group *= 2;
            len++;
        }
        int s = n - group + 1;
        StringBuilder sb = new StringBuilder();
        if(group == s){
            for(int i = 0; i < len; i++){
                sb.append("0");
            }
            return sb.toString();
        }
        sb = new StringBuilder(Integer.toBinaryString(s));
        while(sb.length() < len-1) sb.insert(0, "0");
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getString(n));
    }
}