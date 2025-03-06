// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.
  
// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.
 
 
// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5
 
// Sample Output-1:
// ----------------
// 8
 
// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4
 
// Sample Output-2:
// ----------------
// -1


import java.util.*;
public class Q1{
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
    static int getMinCost(int[][] routes, int[] parent, int n){
        int ans = 0;
        Set<Integer> s = new HashSet<>();
        for(int[] r : routes){
            if(find(r[0], parent) != find(r[1], parent)){
                union(r[0], r[1], parent);
                ans += r[2];
                s.add(r[0]);
                s.add(r[1]);
            }
        }
        return (s.size() < n ? -1 : ans);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] parent = new int[n+1];
        for(int i = 0; i <= n; i++) parent[i] = i;
        int[][] routes = new int[m][3];
        for(int i = 0; i < m; i++){
            routes[i][0] = sc.nextInt();
            routes[i][1] = sc.nextInt();
            routes[i][2] = sc.nextInt();
        }
        Arrays.sort(routes, (a, b) -> a[2] - b[2]);
        System.out.println(getMinCost(routes, parent, n));
    }
}