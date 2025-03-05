// There are N people in a private party. Initially all are strangers to each other,
// and the people are identified by unique ID from 0 to N-1.

// In the party, whenever two persons (person-A and person-B) become friends, they 
// took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
// here T-i indicates time of the photo taken, P-j person with ID 'j', and 
// P-k indicates person with ID 'k'.

// Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
// Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
// friend of person-B", then person-A is friend of person-B.

// You are given L photos information, Your task is to find the earliest time 
// for which every person became friend with every other person in the party.
// If there is no such earliest time, return -1.


// Input Format:
// -------------
// Line-1: Two space separated integers, N and L.
// Next L lines: Three space separated integers, log[i], 0<=i<L.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 6 8
// 5 0 1
// 7 3 4
// 12 2 3
// 21 1 5
// 34 2 4
// 37 0 3
// 42 1 2
// 93 4 5

// Sample Output-1:
// ----------------
// 37


// Sample Input-2:
// ---------------
// 7 6
// 2 0 3
// 5 1 5
// 8 2 5
// 7 3 6
// 9 4 6
// 6 4 5

// Sample Output-2:
// ----------------
// 9


import java.util.*;
public class Q2{
    static int find(int x, int[] parent){
        if(parent[x] != x) parent[x] = find(parent[x], parent);
        return parent[x];
    }
    static void union(int a, int b, int[] parent){
        int p1 = find(a, parent);
        int p2 = find(b, parent);
        if(p1 > p2) parent[p1] = p2;
        else parent[p2] = p1;
    }
    static int getTime(int[][] frnds, int[] parent){
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for(int[] f : frnds){
            if(find(f[1], parent) != find(f[2], parent)){
                union(f[1], f[2], parent);
                s.add(f[1]);
                s.add(f[2]);
                ans = f[0];
            }
        }
        return (s.size() == parent.length ? ans : -1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] frnds = new int[m][3];
        for(int i = 0; i < m; i++){
            frnds[i] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        }
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        Arrays.sort(frnds, (a,b) -> a[0] - b[0]);
        System.out.println(getTime(frnds, parent));
    }
}