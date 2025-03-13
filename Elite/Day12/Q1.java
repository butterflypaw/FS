// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.

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
    static TreeNode buildTree (String s, int[] i){
        if(s.length() == 0) return null;
        StringBuilder temp = new StringBuilder();
        int j = i[0];
        for(j = i[0]; j < s.length(); j++){
            char ss = s.charAt(j);
            if(Character.isDigit(ss) || ss == '-') temp.append(ss);
            else break;
        }
        i[0] = j;
        if(temp.length() != 0){
            TreeNode root = new TreeNode(Integer.parseInt(temp.toString()));
            if(i[0] < s.length() && s.charAt(i[0]) == '('){
                i[0]++;
                root.left = buildTree(s, i);
            }
            if(i[0] < s.length() && s.charAt(i[0]) == ')'){
                i[0]++;
                return root;
            }
            if(i[0] < s.length() && s.charAt(i[0]) == '('){
                i[0]++;
                root.right = buildTree(s, i);
            }
            if(i[0] < s.length() && s.charAt(i[0]) == ')') i[0]++;
            
            return root;
        }
        return null;
    }
    static void levelOrder(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            System.out.println(temp.data);
            if(temp.left  != null) q.add(temp.left);
            if(temp.right  != null) q.add(temp.right);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] i = new int[1]; 
        int[] count = new int[1];
        TreeNode root = buildTree(s, i);
        levelOrder(root);
        sc.close();
    }
}