// The Indian Army has established multiple military camps at strategic locations 
// along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
// a hierarchical structure, with a main base camp acting as the root of the network.

// To fortify national security, the Government of India has planned to deploy 
// a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
// boundary of the network.

// Structure of Military Camps:
//     - Each military camp is uniquely identified by an integer ID.
//     - A camp can have at most two direct connections:
//         - Left connection → Represents a subordinate camp on the left.
//         - Right connection → Represents a subordinate camp on the right.
//     - If a military camp does not exist at a specific position, it is 
//       represented by -1
	
// Mission: Deploying the S.H.I.E.L.D.
// Your task is to determine the list of military camp IDs forming the outer 
// boundary of the military network. This boundary must be traversed in 
// anti-clockwise order, starting from the main base camp (root).

// The boundary consists of:
// 1. The main base camp (root).
// 2. The left boundary:
//     - Starts from the root’s left child and follows the leftmost path downwards.
//     - If a camp has a left connection, follow it.
//     - If no left connection exists but a right connection does, follow the right connection.
//     - The leftmost leaf camp is NOT included in this boundary.
// 3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
// 4. The right boundary (in reverse order):
//     - Starts from the root’s right child and follows the rightmost path downwards.
//     - If a camp has a right connection, follow it.
//     - If no right connection exists but a left connection does, follow the left connection.
//     - The rightmost leaf camp is NOT included in this boundary.


// Input Format:
// -------------
// space separated integers, military-camp IDs.

// Output Format:
// --------------
// Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


// Sample Input-1:
// ---------------
// 5 2 4 7 9 8 1

// Sample Output-1:
// ----------------
// [5, 2, 7, 9, 8, 1, 4]


// Sample Input-2:
// ---------------
// 11 2 13 4 25 6 -1 -1 -1 7 18 9 10

// Sample Output-2:
// ----------------
// [11, 2, 4, 7, 18, 9, 10, 6, 13]


// Sample Input-3:
// ---------------
// 1 2 3 -1 -1 -1 5 -1 6 7

// Sample Output-3:
// ----------------
// [1, 2, 7, 6, 5, 3]

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
    public static void leftView(TreeNode root, List<Integer> arr){
        if(root == null || (root.left == null && root.right == null)) return;
        arr.add(root.data);
        if(root.left == null) leftView(root.right, arr);
        leftView(root.left, arr);
    }
    public static void leaves(TreeNode root, List<Integer> arr){
        if(root == null) return;
        leaves(root.left, arr);
        if(root.left == null && root.right == null) arr.add(root.data);
        leaves(root.right, arr);
    }
    public static void rightView(TreeNode root, List<Integer> arr){
        if(root == null || (root.left == null && root.right == null)) return;
        arr.add(root.data);
        if(root.right == null) rightView(root.left, arr);
        rightView(root.right, arr);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        TreeNode r = root;
        List<Integer> temp = new ArrayList<>();
        System.out.println(root.data);
        leftView(r.left, temp);
        for(int x : temp) System.out.println(x);
        temp.clear();
        leaves(r, temp);
        for(int x : temp) System.out.println(x);
        temp.clear();
        rightView(r.right, temp);
        Collections.reverse(temp);
        for(int x : temp) System.out.println(x);
    }
}