// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true


import java.util.*;
public class Q2{
    static int find(int x, int[] parent){
        if(parent[x] != x){
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
    static void union(int a, int b, int[] parent){
        int p1 = find(a, parent);
        int p2 = find(b, parent);
        if(p1 == p2) return;
        if(p1 > p2) parent[p1] = p2;
        else parent[p2] = p1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] parent = new int[26];
        for(int i = 0; i < 26; i++){
            parent[i] = i;
        }
        int flag = 0;
        for(int i = 0; i < s.length; i++){
            if(s[i].charAt(1) == '=') union(s[i].charAt(0) - 'a', s[i].charAt(3) - 'a', parent);
        }
        for(int i = 0; i < s.length; i++){
            if(s[i].charAt(1) == '!'){
                if(find(s[i].charAt(0) - 'a', parent) == find(s[i].charAt(3) - 'a', parent)){
                    flag = 1;
                    break;
                }
            }
        }
        System.out.println(flag == 1 ? false : true);
        sc.close();
    }
}