package Java;
// Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

// Below is the mapping of digits to letters (as found on a traditional telephone keypad):

// | Digit | Letters       |
// |-------|---------------|
// | 2     | a, b, c       |
// | 3     | d, e, f       |
// | 4     | g, h, i       |
// | 5     | j, k, l       |
// | 6     | m, n, o       |
// | 7     | p, q, r, s    |
// | 8     | t, u, v       |
// | 9     | w, x, y, z    |

// Note: The digit 1 does not correspond to any letters.

// Example 1:
// Input: 23  
// Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

// Example 2:
// Input: 2 
// Output: [a, b, c]


// Constraints:

// - 0 <= digits.length <= 4  
// - Each digit in the input is between '2' and '9'.

import java.util.*;
public class Q3{
    static void backtrack(String n, Map<Character, String> mp, List<String> ans, StringBuilder temp, int ind){
        if(temp.length() == n.length()){
            ans.add(temp.toString());
            return;
        }
        if(ind == n.length()) return;
        String x = mp.get(n.charAt(ind));
        for(int i = 0; i < x.length(); i++){
            temp.append(x.charAt(i));
            backtrack(n, mp, ans, temp, ind + 1);
            temp.deleteCharAt(temp.length()-1);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        Map<Character, String> mp = new HashMap<>();
        mp.put('2', "abc");
        mp.put('3', "def");
        mp.put('4', "ghi");
        mp.put('5', "jkl");
        mp.put('6', "mno");
        mp.put('7', "pqrs");
        mp.put('8', "tuv");
        mp.put('9', "wxyz");
        List<String> ans = new ArrayList<>();
        backtrack(n, mp, ans, new StringBuilder(), 0);
        System.out.println(ans);
    }
}