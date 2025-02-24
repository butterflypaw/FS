// Write a Java program that determines the class of a given IPv4 address. 
// The classification follows the standard IP address classes:
// 	- Class A: IP addresses where the first octet is in the range 1-127.
// 	- Class B: IP addresses where the first octet is in the range 128-191.
// 	- Class C: IP addresses where the first octet is in the range 192-223.
// 	- Multicast (Class D): IP addresses where the first octet is in the range 224-239.

// Input Format:
// -------------
// A string IP: The first network IP address (e.g.,0-239).

// Output Format:
// --------------
// A boolean value, if the two subnets overlap or not.


// Sample Input:
// -------------
// 192.168.1.0

// Sample Output:
// --------------
// Class C

// Explanation:
// ------------
// The first octet 192 is within the Multicast range.

import java.util.*;
public class Q1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] ip = sc.nextLine().split("\\.");
        int firstOctect = Integer.parseInt(ip[0]);
        if(firstOctect >= 1 && firstOctect <= 127) System.out.println("Class A");
        else if(firstOctect >= 128 && firstOctect <= 191) System.out.println("Class B");
        else if(firstOctect >= 192 && firstOctect <= 223) System.out.println("Class C");
        else if(firstOctect >= 224 && firstOctect <= 239) System.out.println("Multicast");
        sc.close();
    }
}