// Imagine you are designing a network of secret corridors in an ancient castle. 
// Each chamber in the castle leads to at most two other chambers through 
// hidden passageways, forming a branching layout. 
// The castle’s "longest secret route" is defined as the maximum number of corridors 
// you must traverse to get from one chamber to another (without repeating the corridor). 
// This route may or may not pass through the main entry chamber.

// Your task is to compute the length of longest secret route between 
// two chambers which is represented by the number of corridors between them.

// Example 1
// input=
// 1 2 3 4 5 
// output=
// 3

// Structure:
//        1
//       / \
//      2   3
//     / \
//    4   5

// Explanation:
// The longest secret route from chamber 4 to chamber 3 
// (alternatively, from chamber 5 to chamber 3) along the route:
// 4 → 2 → 1 → 3
// This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

// Example 2:
// input=
// 1 -1 2 3 4
// output=
// 2

// Structure:
//    1
//     \
//      2
//     / \
//    3   4

// Explanation:
// The longest secret route from chamber 3 to chamber 4 
// (alternatively, from chamber 1 to chamber 4) along the route:
// 3 → 2 → 4
// This path has 2 corridors (3–2, 2–4), so the length is 2.


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
    static int maxi = Integer.MIN_VALUE;
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
    static int maxWidth(TreeNode root){
        if(root == null) return 0;
        int left = maxWidth(root.left);
        int right = maxWidth(root.right);
        maxi = Math.max(maxi, left+right);
        return Math.max(left, right) + 1;
    }
    public static void main(String[] ans){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        int temp = maxWidth(root);
        System.out.println(maxi);
    }
}