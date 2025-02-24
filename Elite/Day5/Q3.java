// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11


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
public class Q3{
    public static TreeNode buildTree(int[] inorder, int is, int ie, Map<Integer, Integer> mp){
        if(is > ie) return null;
        int ind = findIndex(inorder, is, ie, mp);
        TreeNode root = new TreeNode(inorder[ind]);
        if(is == ie) return root;
        root.left = buildTree(inorder, is, ind - 1, mp);
        root.right = buildTree(inorder, ind + 1, ie, mp);
        return root;
    }
    public static int findIndex(int[] inorder, int is, int ie, Map<Integer, Integer> mp){
        int ind = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = is; i <= ie; i++){
            if(mp.get(inorder[i]) <  ind){
                ind = mp.get(inorder[i]);
                ans = i;
            }
        }
        return ans;
    }
    public static List<List<Integer>> bfs(TreeNode root){
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> levels = new ArrayList<>();
        while(!q.isEmpty()){
            int len = q.size();
            List<Integer> subLevel = new ArrayList<>();
            for(int i = 0; i < len; i++)
            {
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
        int[] levelorder = new int[n];
        int[] inorder = new int[n];
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < n; i++) inorder[i] = sc.nextInt();
        for(int i = 0; i < n; i++) levelorder[i] = sc.nextInt();
        for(int i = 0; i < n; i++) mp.put(levelorder[i], i);
        TreeNode root = buildTree(inorder, 0, n-1, mp);
        List<List<Integer>> levels = bfs(root);
        int maxDepth = levels.size();
        int sum = 0;
        for(int x : levels.get(maxDepth - 1)) sum += x;
        System.out.println(maxDepth + " " + sum);
        sc.close();
    }
}