// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        this.right = null;
        this.left = null;
    }
}
public class Q2{
    static TreeNode buildTree(int[] arr, int n){
        if(n == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        q.add(root);
        int i = 1;
        while(i < n){
            TreeNode temp = q.poll();
            if(arr[i] != -1){
                temp.left = new TreeNode(arr[i]);
                q.add(temp.left);
            }
            i++;
            if(i < n && arr[i] != -1){
                temp.right = new TreeNode(arr[i]);
                q.add(temp.right);
            }
            i++;
        }
        return root;
    }
    static int getSum(TreeNode root, Set<Integer> s){
        if(root == null) return 0;
        int sum = root.data + getSum(root.left, s) + getSum(root.right, s);
        s.add(sum);
        return sum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] =Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        Set<Integer> st = new HashSet<>();
        int sum = getSum(root, st);
        int ans = Integer.MIN_VALUE;
        for(int x : st){
            int p = (x * (sum-x)) % 1000000007;
            if(p > ans) ans = p;
        }
        System.out.println(ans);
        sc.close();
    }
}