// In computer networking, subnetting is the process of dividing a larger IP network
// into multiple smaller subnetworks (subnets). Given a base network IP address, 
// a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
// write a Java program that calculates the starting addresses of all the subnets.

// Your program should take as input:
// 	- A base network address (e.g., 192.168.1.0).
// 	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
// 	- The number of subnets to generate.

// The program should then compute and return the starting addresses of each subnet.

// Input Format:
// -------------
// A string NIP: The base network IP address (e.g., "192.168.1.0").
// An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
// An integer num: The number of subnets to be generated.

// Output Format:
// --------------
// A list of subnet addresses, each representing the starting address of a subnet.


// Sample Input:
// -------------
// 192.168.1.0
// 26
// 4

// Sample Output:
// --------------
// [192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0/28, 
// 192.168.1.16/28, 
// 192.168.1.32/28, 
// 192.168.1.48/28

// import java.util.*;
// public class Q3{
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String[] ipString = sc.next().split("\\.");
//         int cidr = sc.nextInt();
//         int num = sc.nextInt();
//         int ip = 0;
//         for (String p : ipString) ip = (ip << 8) | Integer.parseInt(p);
//         int temp = num;
//         while(temp > 1){
//             temp /= 2;
//             diff++;
//         }
//         int newCIDR = cidr + diff;
//         int subnetSize = 1 << (32 - newCIDR);
//         List<String> subnets = new ArrayList<>();
//         for (int i = 0; i < num; i++) {
//             int subnetIP = ip + (i * subnetSize);
//             String subnetAddr = String.format("%d.%d.%d.%d/%d",
//                 (subnetIP >> 24) & 0xFF,
//                 (subnetIP >> 16) & 0xFF,
//                 (subnetIP >> 8) & 0xFF,
//                 subnetIP & 0xFF,
//                 newCIDR);
//             subnets.add(subnetAddr);
//         }
//         System.out.println(subnets);
//         sc.close();
//     }
// }
import java.util.*;
public class Q2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.next().split("\\.");
        int cidr = sc.nextInt();
        int num = sc.nextInt();
        int newCidr = cidr + (int)Math.ceil(Math.log(num)/Math.log(2));
        int available = (1 << (32-newCidr));
        String[] ans = new String[num];
        for(int i = 0; i < num; i++){
            ans[i] = inp[0] + "." + inp[1] + "." + inp[2] + "." + ((available*i) + Integer.parseInt(inp[3])) + "/" + newCidr;
        }
        System.out.println(Arrays.toString(ans));
    }
}