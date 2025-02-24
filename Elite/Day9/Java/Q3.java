// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

import java.util.*;
class TreeNode{
    int data;
    int level;
    TreeNode left;
    TreeNode right;
    TreeNode(int data, int level){
        this.data = data;
        this.level = level;
        this.left = null;
        this.right = null;
    }
}
public class Q3{
    static TreeNode buildTree(int[] arr, int n){
        if(n == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0], 0);
        q.add(root);
        int i = 1;
        while(i < n){
            TreeNode temp = q.poll();
            if(arr[i] != -1){
                temp.left = new TreeNode(arr[i], temp.level-1);
                q.add(temp.left);
            }
            i++;
            if(i < n && arr[i] != -1){
                temp.right = new TreeNode(arr[i], temp.level+1);
                q.add(temp.right);
            }
            i++;
        }
        return root;
    }
    static void getVerticalOrder(TreeNode root, TreeMap<Integer, List<Integer>> mp){
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                if(q.peek().left != null) q.add(q.peek().left);
                if(q.peek().right != null) q.add(q.peek().right);
                int l = q.peek().level;
                if(!mp.containsKey(l)) mp.put(l, new ArrayList<>());
                mp.get(l).add(q.peek().data);
                q.poll();
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[]  s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        TreeNode root = buildTree(nodes, n);
        TreeMap<Integer, List<Integer>> mp = new TreeMap<>();
        getVerticalOrder(root, mp);
        for(int x : mp.keySet()) System.out.println(mp.get(x));
        sc.close();
    }
}