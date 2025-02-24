// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.


// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6


// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6

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
    static TreeNode buildTree(int[] arr, int n){
        if(n == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        q.add(root);
        int i = 1;
        while(i < n){
            TreeNode temp = q.poll();
            if(arr[i] != -1){
                temp.left = new TreeNode(arr[i]);
                q.add(temp.left);
            }
            i++;
            if(i < n && arr[i] != -1){
                temp.right = new TreeNode(arr[i]);
                q.add(temp.right);
            }
            i++;
        }
        return root;
    }
    static void toList(TreeNode root){
        TreeNode temp = root;
        while(temp != null){
            if(temp.left != null){
                TreeNode l = temp.left;
                while(l.right != null) l = l.right;
                // System.out.println(l.data);
                l.right = temp.right;
                temp.right = temp.left;
                temp.left = null;
            }
            temp = temp.right;
        }
    }
    static void printTree(TreeNode root){
        if(root == null) return;
        System.out.println(root.data);
        printTree(root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        toList(root);
        printTree(root);
        sc.close();
    }
}