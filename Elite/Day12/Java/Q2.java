// Imagine a company where each employee has a performance score, and 
// the organizational chart is structured as a binary tree with the CEO at the top. 
// An employee is considered "outstanding" if, along the chain of command from the 
// CEO down to that employee, no one has a performance score higher than that 
// employee's score. Your task is to determine the total number of outstanding 
// employees in the company.

// Example 1:
// Input: 3 1 4 3 -1 1 5
// Output: 4

// Chart formed:
//          3
//         / \
//        1   4
//       /   / \
//      3   1   5

// Explanation:
// - The CEO (score 3) is automatically outstanding.
// - The employee with score 4, whose chain is [3,4], is outstanding because 4 
//   is higher than 3.
// - The employee with score 5, following the path [3,4,5], is outstanding as 5 
//   is the highest so far.
// - The subordinate with score 3, along the path [3,1,3], is outstanding because 
//   none of the managers in that chain have a score exceeding 3.

// Example 2:
// Input: 3 3 -1 4 2
// Output: 3

// Chart formed:
//        3
//       / 
//      3
//     / \
//    4   2

// Explanation:
// - The CEO (score 3) is outstanding.
// - The subordinate with score 3 (chain: [3,3]) is outstanding.
// - The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
//   managers have higher scores.

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
public class Q2{
    static int ans = 0;
    static void getPath(TreeNode root, int maxi){
        if(root == null) return;
        if(root.left != null){
            if(root.left.data >= maxi){
                ans++;
                maxi = root.left.data;
            }
            getPath(root.left, maxi);
        }
        if(root.right != null){
            if(root.right.data >= maxi){
                ans++;
                maxi = root.right.data;
            }
            getPath(root.right, maxi);
        }
    }
    static TreeNode buildTree(int[] nodes, int n){
        if(n == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(nodes[0]);
        int i = 1;
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
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       String[] s = sc.nextLine().split(" ");
       int n = s.length;
       int[] nodes = new int[n];
       for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
       TreeNode root = buildTree(nodes, n);
       int maxi = root.data;
       getPath(root, maxi);
       System.out.println(ans+1);
    }
}