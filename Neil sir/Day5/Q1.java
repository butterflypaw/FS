// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

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
    public static TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, Map<Integer, Integer> mp){
        if(is > ie || ps > pe) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int rootIndex = mp.get(postorder[pe]);
        int leftLen = rootIndex - is;
        root.left = buildTree(inorder, is, rootIndex-1, postorder, ps, ps+leftLen-1, mp);
        root.right = buildTree(inorder, rootIndex+1, ie, postorder, ps+leftLen, pe-1, mp);
        return root;
    }
    public static List<List<Integer>> levelOrder(TreeNode root){
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> levels = new ArrayList<>();
        q.add(root);
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
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] postorder = new int[n];
        for(int i = 0; i < n; i++) inorder[i] = sc.nextInt();
        for(int i = 0; i < n; i++) postorder[i] = sc.nextInt();
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for(int i = 0; i < q; i++){
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < n; i++) mp.put(inorder[i], i);
        TreeNode root = buildTree(inorder, 0, n-1, postorder, 0, n-1, mp);
        List<List<Integer>> levels = levelOrder(root);
        for(int i = 0; i < q; i++){
            int l = queries[i][0];
            int h = queries[i][1];
            List<Integer> level = new ArrayList<>();
            for(int j = l; j <= h; j++){
                for(int x : levels.get(j-1)) level.add(x);
            }
            System.out.println(level);
        }
    }
}