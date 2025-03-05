// In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
// You have two encoded keys, key1 and key2, both of equal length. Each character 
// in key1 is paired with the corresponding character in key2. 

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.  
// • Mutual Mapping: If a character from key1 maps to one in key2, then that 
//   character in key2 maps back to the one in key1.  
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
//   are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every character 
// with the smallest equivalent character from its equivalence group. 
// The result should be the relatively smallest possible decoded message.


// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------
// Print a string, decoded message which is relatively smallest string of cipherText.

// Example 1: 
// input=
// attitude progress apriori
// output=
// aaogoog


// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
// [d, e, s]. By substituting each character in cipherText with the smallest from 
// its group, you decode the message to "aaogoog".


// Constraints:  
// • 1 <= key1.length, key2.length, cipherText.length <= 1000  
// • key1.length == key2.length  
// • key1, key2, and cipherText consist solely of lowercase English letters.


import java.util.*;
public class Q2{
    static int find(int ind, int[] parent){
       if(ind == parent[ind]) return ind;
       return parent[ind] = find(parent[ind], parent);
    }
    static void union (char a, char b, int[] parent){
        int p1 = find(a-'a', parent);
        int p2 = find(b-'a', parent);
        if(p1 == p2) return;
        if(p1 > p2) parent[p1] = p2;
        else parent[p2] = p1;
    }
    public static String getCypher(String s, int[] parent){
        StringBuilder ss = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            int ind = find(s.charAt(i)-'a', parent);
            ss.append((char)(ind + 97));
        }
        return ss.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next(), s2 = sc.next(), s3 = sc.next();
        int[] parent = new int[26];
        for(int i = 0; i < 26; i++){
            parent[i] = i;
        }
        for(int i = 0; i < s1.length(); i++){
            union(s1.charAt(i), s2.charAt(i), parent);
        }
        System.out.println(getCypher(s3, parent));
    }
}