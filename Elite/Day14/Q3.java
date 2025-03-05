// Imagine you are a secret agent tasked with sending encoded messages. 
// Each original message is a string of lowercase letters, and you can disguise 
// it by replacing selected, non-adjacent segments with the count of characters 
// in those segments. However, the encoding must follow strict rules: only 
// non-empty segments can be replaced, replacements cannot be adjacent, and any 
// numbers used must not have leading zeros.

// For instance, the message "substitution" can be encoded in various ways, such as:

// • "s10n" (keeping 's', replacing the next 10 characters with 10, and ending with 'n')  
// • "sub4u4" (keeping "sub", replacing 4 characters, then 'u', and replacing 4 more characters)  
// • "12" (replacing the entire message with its length)  
// • "su3i1u2on" (using a different pattern of replacements)  
// • "substitution" (leaving the message unaltered)

// Invalid encodings include:

// • "s55n" (because the replaced segments are adjacent)  
// • "s010n" (the number 010 has a leading zero)  
// • "s0ubstitution" (attempts to replace an empty segment)

// Your task is: given an original message and an encoded version, 
// determine if the encoded version is a valid secret code for the message.

// Example 1:

// Input: 
// internationalization
// i12iz4n
  
// Output: 
// true  

// Explanation: "internationalization" can be encoded as "i12iz4n" by keeping 'i', 
// replacing 12 letters, keeping "iz", replacing 4 letters, and then ending with 'n'.

// Example 2:

// Input: 
// apple
// a2e
  
// Output: 
// false  

// Explanation: The encoding "a2e" does not correctly represent "apple".

// Time Complexity: O(n) where n=max(len(word),len(abbr))
// Auxiliary Space:  O(1).

import java.util.*;
public class Q3{
    static boolean isValid(String s1, String s2){
        int ind = 0;
        for(int i = 0; i < s2.length(); i++){
            if(Character.isDigit(s2.charAt(i))){
                int num = 0;
                if(s2.charAt(i) == '0') return false;
                while(i < s2.length() && Character.isDigit(s2.charAt(i))){
                    num = num*10 + (s2.charAt(i) - '0');
                    i++;
                }
                i--;
                if(num > s1.length()-ind) return false;
                ind += num;
            }
            else{
                if(s1.charAt(ind) != s2.charAt(i)) return false;
                ind++;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(isValid(s1, s2));
        sc.close();
    }
}