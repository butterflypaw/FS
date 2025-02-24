// Balbir Singh is working with networked systems, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false

import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class Q1{
    static TreeNode buildTree(int[] nodes, int n){
        if(n == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(nodes[0]);
        q.add(root);
        int i = 1;
        while(i < n){
            TreeNode node = q.poll();
            if(nodes[i] != -1){
                node.left = new TreeNode(nodes[i]);
                q.add(node.left);
            }
            i++;
            if(i < n && nodes[i] != -1){
                node.right = new TreeNode(nodes[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    static int getSum(TreeNode root){
        if(root == null) return 0;
        return root.data + getSum(root.left) + getSum(root.right);
    }
    static boolean isEqual(TreeNode root, int sum){
        if(root == null) return false;
        if((getSum(root) == sum)) return true;
        return isEqual(root.left, sum) || isEqual(root.right, sum);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        int sum = getSum(root);
        if(sum %2 != 0) System.out.println(false);
        else System.out.println(isEqual(root, sum/2));
        sc.close();
    }
}