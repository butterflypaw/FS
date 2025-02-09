// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7

import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class Q4program{
    public static TreeNode buildTree(int arr[])
    {
        if(arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0]);
        if(arr.length == 1) return root;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty() && i < arr.length)
        {
            TreeNode temp = q.poll();
            if(arr[i] != -1){
                temp.left = new TreeNode(arr[i]);
                q.offer(temp.left);
            }
            i++;
            if(i < arr.length && arr[i] != -1){
                temp.right = new TreeNode(arr[i]);
                q.offer(temp.right);
            }
            i++;
        }
        return root;
    }
    public static void inOrder(TreeNode root)
    {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i = 0; i < s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        TreeNode root = buildTree(arr);
        inOrder(root);
    }
}