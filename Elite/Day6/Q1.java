// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

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
    public static TreeNode buildTree(int[] preorder, int pres, int pree, int[] postorder, int poss, int pose){
        if(pres > pree || poss > pose) return null;
        TreeNode root = new TreeNode(preorder[pres]);
        if(pres + 1 > pree) return root;
        int index = findIndex(postorder, poss, pose, preorder[pres+1]);
        int leftLen = index - poss;
        root.left = buildTree(preorder, pres+1, pres+leftLen+1, postorder, poss, index);
        root.right = buildTree(preorder, pres+leftLen+2, pree, postorder, index+1, pose-1);
        return root;
    }
    public static int findIndex(int[] postorder, int l, int h, int num){
        for(int i = l; i <= h; i++){
            if(postorder[i] == num) return i;
        }
        return -1;
    }
    public static List<List<Integer>> levelOrder(TreeNode root){
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> levels = new ArrayList<>();
        while(!q.isEmpty()){
            int len = q.size();
            List<Integer> subLevel = new ArrayList<>();
            for(int i = 0; i < len; i++){
                if(q.peek().left != null) q.add(q.peek().left);
                if(q.peek().right != null) q.add(q.peek().right);
                subLevel.add(q.poll().data);
            }
            levels.add(subLevel);
        }
        return levels;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] pre = sc.nextLine().split(" ");
        String[] post = sc.nextLine().split(" ");
        int n = pre.length;
        int[] preorder = new int[n];
        int[] postorder = new int[n];
        for(int i = 0; i < n; i++) preorder[i] = Integer.parseInt(pre[i]);
        for(int i = 0; i < n; i++) postorder[i] = Integer.parseInt(post[i]);
        TreeNode root = buildTree(preorder, 0, n-1, postorder, 0, n-1);
        List<List<Integer>> levels = levelOrder(root);
        System.out.println(levels);
        sc.close();
    }
}