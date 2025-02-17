package Java;
// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.

import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        this.right = null;
        this.left = null;
    }
}
public class Q1{
    static TreeNode buildTree(int[] arr, int n){
        if(arr.length == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        int i = 1;
        q.add(root);
        while(i < n){
            TreeNode node = q.poll();
            if(arr[i] != -1){
                TreeNode newNode = new TreeNode(arr[i]);
                node.left = newNode;
                q.add(node.left);
            }
            i++;
            if(i < n && arr[i] != -1){
                TreeNode newNode = new TreeNode(arr[i]);
                node.right = newNode;
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    static TreeNode findLca(TreeNode root, int source, int target){
        if(root == null || root.data == source || root.data == target) return root;
        TreeNode left = findLca(root.left, source, target);
        TreeNode right = findLca(root.right, source, target);
        if(left != null && right != null) return root;
        if(left != null) return left;
        else return right;
    }
    static int getDistance(TreeNode root, int target, int level){
        if(root == null) return -1;
        if(root.data == target) return level;
        int left = getDistance(root.left, target, level+1);
        int right = getDistance(root.right, target, level+1);
        if(left != -1) return left;
        else return right;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int source = sc.nextInt();
        int target = sc.nextInt();
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        TreeNode lca = findLca(root, source, target);
        int dist1 = getDistance(lca, source, 0);
        int dist2 = getDistance(lca, target, 0);
        System.out.println(dist1 + dist2);
    }
}