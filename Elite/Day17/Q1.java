// There are N computers in a network, all the computers are connected as tree 
// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.


// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.


// Sample Input-1:
// ---------------
// 6
// 1 2
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// Sample Output-1:
// ---------------
// 5 6


// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4

// Sample Output-2:
// ---------------
// 2 4

import java.util.*;
public class Q1{
    static void union(int a, int b, int[] parent){
        int p1 = find(a, parent);
        int p2 = find(b, parent);
        if(p1 == p2) return;
        if(p1 > p2) parent[p1] = p2;
        else parent[p2] = p1;
    }
    static int find(int x, int[] parent){
        if(parent[x] != x) parent[x] = find(parent[x], parent);
        return parent[x];
    }
    static void getEdge(int[][] edges, int[] parent, List<int[]> req){
        for(int i=0; i < edges.length; i++){
            if(find(edges[i][0], parent) == find(edges[i][1], parent)){
                req.add(new int[]{edges[i][0], edges[i][1]});
            }
            union(edges[i][0], edges[i][1], parent);
        }
        return;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }
        int[][] edges = new int[n][2];
        for(int i = 0; i < n; i++){
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }
        List<int[]> req = new ArrayList<>();
        getEdge(edges, parent, req);
        System.out.println(Arrays.toString(req.get(req.size()-1)));
    }
}