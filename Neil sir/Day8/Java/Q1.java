package Java;
// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4

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
    static List<List<Integer>> printTree(TreeNode root){
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> levels = new ArrayList<>();
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> subLevel = new ArrayList<>();
            for(int i = 0; i < size; i++){
                if(q.peek().left != null) q.add(q.peek().left);
                if(q.peek().right != null) q.add(q.peek().right);
                subLevel.add(q.poll().data);
            }
            levels.add(subLevel);
        }
        return levels;
    }
    static TreeNode upsideDown(TreeNode root){
        if(root == null || root.left == null) return root;
        TreeNode newRoot = upsideDown(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int  i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        TreeNode newRoot = upsideDown(root);
        System.out.println(printTree(newRoot));
   }
}