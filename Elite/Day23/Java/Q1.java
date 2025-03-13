// Imagine you are designing a grand castle where each room holds a specific amount 
// of treasure. The castle is built in a binary layout, meaning every room may lead 
// to two adjacent wings—a left wing and a right wing. 

// An "organized section" of the castle follows this rule: for any given room, 
// every room in its left wing contains a treasure value that is strictly less 
// than the current room’s value, and every room in its right wing contains a 
// value that is strictly greater. Additionally, each wing must itself be organized
// according to the same rule.

// Your challenge is to determine the maximum total treasure (i.e., the sum of 
// treasure values) that can be gathered from any such organized section of the castle.

// Example 1:
// input=
// 1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
// output=
// 20

// Castle:
//           1
//         /   \
//        4     3
//       / \   / \
//      2   4 2   5
//               / \
//              4   6

// Explanation: The best organized section starts at the room with a treasure value 
// of 3, yielding a total treasure of 20.

// Example 2:
// input=
// 4 3 -1 1 2
// output=
// 2

// Castle:
//     4
//    /
//   3
//  / \
// 1   2

// Explanation: The optimal organized section is just the single room with a 
// treasure value of 2.

// Example 3:
// input=
// -4 -2 -5
// output=
// 0

// Castle:
//    -4
//   /  \
// -2   -5
 
// Explanation: Since all rooms contain negative treasure values, no beneficial 
// organized section exists, so the maximum total treasure is 0.

// Constraints:

// - The number of rooms in the castle ranges from 1 to 40,000.
// - Each room’s treasure value is an integer between -40,000 and 40,000.

import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        left = right = null;
    }
}
public class Q1{
    static TreeNode buildTree(int[] nodes, int n){
        if(n == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        int i = 1;
        TreeNode root = new TreeNode(nodes[0]);
        q.add(root);
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
    static boolean isBst(TreeNode root, int min, int max){
        if(root == null) return true;
        if(root.data <= min || root.data >= max) return false;
        if(isBst(root.left, min, root.data) && isBst(root.right, root.data, max)) return true;
        return false;
    }
    static void helper(TreeNode root, int[] ans){
        if(root == null) return;
        if(isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) ans[0] = Math.max(ans[0], sum(root));
        helper(root.left, ans);
        helper(root.right, ans);
    }
    static int sum(TreeNode root){
        if(root == null) return 0;
        return root.data + sum(root.left) + sum(root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        int[] ans = new int[1];
        ans[0] = Integer.MIN_VALUE;
        helper(root, ans);
        System.out.println(ans[0] <= 0 ? 0 : ans[0]);
    }
}