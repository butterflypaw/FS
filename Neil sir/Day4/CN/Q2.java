// /*
// Write a program that takes an IP address and subnet mask (in CIDR notation, 
// e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

// Input Format:
// ---------------
// A String, IPAddress
// An integer, CIDR

// Output Format:
// ---------------
// Space separated IP addresses, network IP and broadcast IP.


// Sample Input-1:
// -----------------
// 192.168.1.10
// 24

// Sample Output-1:
// ------------------
// 192.168.1.0 192.168.1.255


// Sample Input-2:
// -----------------
// 192.0.2.1
// 24

// Sample Output-2:
// ------------------
// 192.0.2.0 192.0.2.255

// */
import java.util.*;
public class Q2{
    public static String getIp(int n)
    {
        String ans = String.format("%d.%d.%d.%d", 
        (n >> 24) & 0xFF,
        (n >> 16) & 0xFF,
        (n >> 8) & 0xFF,
        n & 0xFF);
        return ans;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        int mask = 0xFFFFFFFF << (32-n);
        String[] temp = s.split("\\.");
        int ip = 0;
        for(String t : temp) ip = (ip << 8) | Integer.parseInt(t);
        int network = ip & mask;
        int broadcast = ip | ~mask;
        System.out.println(getIp(network) + " " + getIp(broadcast));
    }
}