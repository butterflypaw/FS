// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)


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
    public static TreeNode buildTree(int[] inorder, int is, int ie, int[] preorder, int ps, int pe){
        if(ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(preorder[ps]);
        int rootIndex = findIndex(preorder[ps], is, ie, inorder);
        int len = rootIndex - is;
        root.left = buildTree(inorder, is, rootIndex-1, preorder, ps+1, ps+len);
        root.right = buildTree(inorder, rootIndex+1, ie, preorder, ps+len+1, pe);
        return root;
    }
    public static int findIndex(int num, int l, int h, int[] inorder){
        for(int i = l; i <= h; i++){
            if(inorder[i] == num) return i;
        }
        return -1;
    }
    public static List<List<Integer>> levelOrder(TreeNode root){
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> levels = new ArrayList<>();
        int numLevel = 1;
        while(!q.isEmpty()){
            int len = q.size();
            List<Integer> subLevel = new ArrayList<>();
            for(int i = 0; i < len; i++){
                if(q.peek().left != null) q.add(q.peek().left);
                if(q.peek().right != null) q.add(q.peek().right);
                subLevel.add(q.poll().data);
            }
            if(numLevel % 2 == 0) Collections.reverse(subLevel);
            levels.add(subLevel);
            numLevel++;
        }
        return levels;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] preorder = new int[n];
        for(int i = 0; i < n; i++) inorder[i] = sc.nextInt();
        for(int i = 0; i < n; i++) preorder[i] = sc.nextInt();
        int l = sc.nextInt(), h = sc.nextInt();
        TreeNode root = buildTree(inorder, 0, n-1, preorder, 0, n-1);
        List<List<Integer>> levels = levelOrder(root);
        for(int i = l-1; i < h; i++){
            System.out.println(levels.get(i));
        }
    }
}