// A software development company is designing a smart home automation 
// system that uses sensor networks to monitor and control different devices 
// in a house. The sensors are organized in a hierarchical structure, where each 
// sensor node has a unique ID and can have up to two child nodes (left and right).

// The company wants to analyze the left-most sensors in the system to determine
// which ones are critical for detecting environmental changes. The hierarchy of 
// the sensors is provided as a level-order input, where missing sensors are 
// represented as -1.

// Your task is to build the sensor network as a binary tree and then determine 
// the left-most sensor IDs at each level.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the left-most sensor IDs at each level


// Sample Input-1:
// ---------------
// 1 2 3 4 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 2, 4]



// Sample Input-2:
// ---------------
// 3 2 4 1 5

// Sample Output-2:
// ----------------
// [3, 2, 1]

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
public class Q4{
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
    public static void leftView(TreeNode root, int level, List<Integer> arr){
        if(root == null) return;
        if(arr.size() == level) arr.add(root.data);
        leftView(root.left, level+1, arr);
        leftView(root.right, level+1, arr);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(arr, n);
        List<Integer> left = new ArrayList<>();
        leftView(root, 0, left);
        System.out.println(left);
        sc.close();
    }
}