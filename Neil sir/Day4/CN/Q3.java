
// Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
// whether IP-1 and IP-2 belongs to same host range or not.

// Input Format:
// ---------------
// Two space separated strings, IP1 and IP2.
// An integer, CIDR (subnet mask).

// Output Format:
// ---------------
// A boolean result.


// Sample Input-1:
// -----------------
// 192.168.1.10 192.168.1.20
// 24

// Sample Output-1:
// ------------------
// true


// Sample Input-2:
// -----------------
// 192.0.2.1 192.0.3.253
// 24

// Sample Output-2:
// ------------------
// false

import java.util.*;
public class Q3{
    public static int checkIp(int n, int mask){
        return n & mask;
    }
    public static int getIp(String s)
    {
        String[] temp = s.split("\\.");
        int ip = 0;
        for(String t : temp) ip = (ip << 8) | Integer.parseInt(t);
        return ip;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next(), s2 = sc.next();
        int n = sc.nextInt();
        int mask = 0xFFFFFFFF << (32-n);
        int ip1 = getIp(s1);
        int ip2 = getIp(s2);
        System.out.println(checkIp(ip1, mask) == checkIp(ip2, mask) ? true : false);
    }
}