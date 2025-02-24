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
    public static int getIp(String s){
        String[] temp = s.split("\\.");
        int ip = 0;
        for(String t : temp) ip = (ip << 8) | Integer.parseInt(t);
        return ip;
    }
    public static String getString(int s){
        return String.format("%d.%d.%d.%d", (s >> 24) & 0xFF, (s >> 16) & 0xFF, (s >> 8) & 0xFF, s & 0xFF);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = sc.nextInt();
        int mask = 0xFFFFFFFF << (32-n);
        int ip = getIp(s);
        int network = ip & mask;
        int broadcast = ip | ~mask;
        System.out.println(getString(network) + " " + getString(broadcast));
        sc.close();
    }
}