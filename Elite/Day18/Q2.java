// Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
// cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// The puzzle board has some patterns formed with boxes in it, 
// the patterns may be repeated. The patterns are formed with boxes (1's) only, 
// that are connected horizontally and vertically but not diagonally.

// Pranav wants to find out the number of unique patterns in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of unique patterns in 
// the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the grid-land.
// Next M lines: contains N space-separated integers [0, 1].

// Output Format:
// --------------
// Print an integer, the number of unique patterns in the puzzle board.


// Sample Input-1:
// ---------------
// 5 5
// 0 1 0 1 1
// 1 1 1 0 1
// 0 1 0 1 0
// 1 0 1 1 1
// 1 1 0 1 0

// Sample Output-1:
// ----------------
// 3

// Explanation-1:
// ------------
// The unique patterns are as follows:
//   1			1 1	    1 
// 1 1 1		  1 ,	1 1
//   1	   ,	
   
   
// Sample Input-2:
// ---------------
// 6 6
// 1 1 0 0 1 1
// 1 0 1 1 0 1
// 0 1 0 1 0 0
// 1 1 0 0 0 1
// 0 0 1 0 1 1
// 1 1 0 1 0 0

// Sample Output-2:
// ----------------
// 5

// Explanation-2:
// ------------
// The unique patterns are as follows:
// 1 1		1 1		    1		1 1	,	1
// 1   ,     1 ,	    1 1 ,		


import java.util.*;
public class Q2 {
    static int find(int x, int[] parent) {
        if (parent[x] != x) parent[x] = find(parent[x], parent);
        return parent[x];
    }
    static void union(int a, int b, int[] parent) {
        int p1 = find(a, parent);
        int p2 = find(b, parent);
        if (p1 > p2) parent[p1] = p2;
        else parent[p2] = p1;
    }
    static int getIslands(int[][] mat, int[] parent, int n, int m) {
        Map<Integer, List<int[]>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    if (i + 1 < n && mat[i + 1][j] == 1) union(i * m + j, (i + 1) * m + j, parent);
                    if (j + 1 < m && mat[i][j + 1] == 1) union(i * m + j, i * m + (j + 1), parent);
                    if (i > 0 && mat[i - 1][j] == 1) union(i * m + j, (i - 1) * m + j, parent);
                    if (j > 0 && mat[i][j - 1] == 1) union(i * m + j, i * m + (j - 1), parent);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    int temp = find(i * m + j, parent);
                    mp.computeIfAbsent(temp, k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }
        Set<String> ans = new HashSet<>();
        for (List<int[]> l : mp.values()) {
            l.sort(Comparator.comparingInt(a -> a[0] * m + a[1]));
            int i = l.get(0)[0], j = l.get(0)[1];
            StringBuilder sb = new StringBuilder();
            for (int[] point : l) {
                sb.append((point[0] - i) + "," + (point[1] - j) + ";");
            }
            ans.add(sb.toString());
        }
        return ans.size();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        int[] parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }
        System.out.println(getIslands(mat, parent, n, m));
    }
}
