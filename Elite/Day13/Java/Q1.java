// Imagine you are navigating a maze where each path you take has a section with a 
// code. The maze is structured as a series of interconnected rooms, 
// represented as a TreeNode structure. Each room in the maze has a code (integral value)
// associated with it, and you are trying to check if a given sequence of code 
// matches a valid path from the entrance to an exit. 

// You are provide with the maze structure, where you have to build the maze and then,
// you are provided with a series of space seperated digits, representing a journey 
// starting from the entrance and passing through the rooms along the way. 
// The task is to verify whether the path corresponding to this nodesay of codes 
// exists in the maze.

// Example 1:
// Input:
// 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
// 0 1 0 1                         //path to verify

// Output: true

// Explanation:
//                0
//              /   \
//             1     0
//            / \    /
//           0   1  0
//            \  / \
//             1 0  0

// The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
// Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


// Example 2:
// Input:
// 1 2 3
// 1 2 3

// output: false

// Explanation:
// The proposed path 1 → 2 → 3 does not exist in the maze, 
// so it cannot be a valid path.
import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data=data;
        left = right = null;
    }
}    
public class Q1{
    public static TreeNode buildTree(int[] nodes,int n){
        if(n == 0)return null;
        TreeNode root = new TreeNode(nodes[0]);
        int i = 1;
        Queue<TreeNode> q = new LinkedList<>();
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
    public static boolean getPath(TreeNode root,int[] path,int ind ){ 
        if(root == null) return false;
        if(ind >= path.length) return true;   
        if(path[ind] != root.data) return false;
        if(getPath(root.left, path, ind+1) || getPath(root.right, path, ind+1)) return true;
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s1 = sc.nextLine().split(" ");
        String[] s2 = sc.nextLine().split(" ");
        int n = s1.length;
        int m = s2.length;
        int[] nodes = new int[n];
        int[] path = new int[m];
        for(int i = 0; i < n; i++) nodes[i]=Integer.parseInt(s1[i]);
        for(int i = 0; i < m; i++) path[i]=Integer.parseInt(s2[i]);
        TreeNode root = buildTree(nodes, n);
        System.out.println(getPath(root, path, 0));
        sc.close();
    }

}
        

