// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]


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
public class Q2{
    public static TreeNode buildTree(int[] arr, int n){
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        q.add(root);
        int i = 1;
        while(i < n){
            TreeNode node = q.poll();
            if(arr[i] != -1){
                TreeNode temp = new TreeNode(arr[i]);
                node.left = temp;
                q.add(node.left);
            }
            i++;
            if(i < n && arr[i] != -1){
                TreeNode temp = new TreeNode(arr[i]);
                node.right = temp;
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static void rightSideView (TreeNode root, List<Integer> rightView, int level){
        if(root == null) return;
        if(rightView.size() == level) rightView.add(root.data);
        rightSideView(root.right, rightView, level+1);
        rightSideView(root.left, rightView, level+1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(arr, n);
        List<Integer> rightView = new ArrayList<>();
        rightSideView(root, rightView, 0);
        System.out.println(rightView);
    }
}