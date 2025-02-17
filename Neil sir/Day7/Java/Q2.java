package Java;
// Mr. Rakesh is interested in working with Data Structures.

// He has constructed a Binary Tree (BT) and asked his friend 
// Anil to check whether the BT is a self-mirror tree or not.

// Can you help Rakesh determine whether the given BT is a self-mirror tree?
// Return true if it is a self-mirror tree; otherwise, return false.

// Note:
// ------
// In the tree, '-1' indicates an empty (null) node.

// Input Format:
// -------------
// A single line of space separated integers, values at the treenode

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 2 1 1 2 3 3 2

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 2 1 1 -1 3 -1 3

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
        this.right = null;
        this.left = null;
    }
}
public class Q2{
    static TreeNode buildTree(int[] arr, int n){
        if(n == 0) return null;
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
    static boolean symmetric(TreeNode root){
        if(root == null) return true;
        return isSymmetric(root.right, root.left);
    }
    static boolean isSymmetric(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null || left.data != right.data) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        System.out.println(symmetric(root));
    }
}