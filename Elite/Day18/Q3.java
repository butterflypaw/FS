// "Emphatic Pronunciation" of a given word is where we take the word and
// replicate some of the letter to emphasize their impact.

// Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
// We define emphatic pronunciation of a word, which is derived by replicating 
// a group (or single) of letters in the original word. 

// So that the replicated group is atleast 3 characters or more and 
// greater than or equal to size of original group. 
// For example Good -> Goood is an emphatic pronunciation,
// but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
// In the question you are given the "Emphatic pronunciation" word, 
// you have to findout how many words can legal result in the 
// "emphatic pronunciation" word.

// Input Format:
// -------------
// Line-1 -> A String contains a single word, Emphatic Pronunciation word
// Line-2 -> Space seperated word/s

// Output Format:
// --------------
// Print an integer as your result


// Sample Input-1:
// ---------------
// goood
// good goodd

// Sample Output-1:
// ----------------
// 1


// Sample Input-2:
// ---------------
// heeelllooo
// hello hi helo

// Sample Output-2:
// ----------------
// 2

import java.util.*;
public class Q3{
    public static boolean isValid(String s, String word) {
        int m = s.length(), n = word.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) != word.charAt(j)) return false;
            int c1 = 0, c2 = 0;
            char ch = s.charAt(i);
            while (i < m && s.charAt(i) == ch) {
                c1++;
                i++;
            }
            while (j < n && word.charAt(j) == ch) {
                c2++;
                j++;
            }
            if (c1 < c2 || (c1 > c2 && c1 <= 2)) return false;
        }
        return i == m && j == n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] words = sc.nextLine().split(" ");
        int count = 0;
        for (String w : words) {
            if (isValid(s, w)) count++;
        }
        System.out.println(count);
    }
}

