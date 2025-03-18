// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 


// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014
                  
// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.

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
public class Q4{
    static TreeNode root = null;
    static TreeNode insert(TreeNode root, int x){
        if(root == null){
            root = new TreeNode(x);
            return root;
        }
        if(x < root.data) root.left = insert(root.left, x);
        else if (x > root.data) root.right = insert(root.right, x);
        return root;
    }
    static void buildTree(int[] arr){
        for(int x : arr){
            root = insert(root, x);
        }
    }
    static void find(int x, TreeNode root, int[] a, int[] b){
        if(root == null) return;
        if(root.data >= x){
            b[0] = Math.min(b[0], root.data);
            find(x, root.left, a, b);
        }
        if (root.data <= x){
            a[0] = Math.max(a[0], root.data);
            find(x, root.right, a, b);
        }
    }
    static void getNodes(int[] query, TreeNode root, List<List<Integer>> ans){
        for(int x : query){
            int[] a = new int[1];
            int[] b = new int[1];
            a[0] = Integer.MIN_VALUE;
            b[0] = Integer.MAX_VALUE;
            find(x, root, a, b);
            if(a[0] == Integer.MIN_VALUE) a[0] = -1;
            if(b[0] == Integer.MAX_VALUE) b[0] = -1;
            List<Integer> temp = new ArrayList<>();
            temp.add(a[0]);
            temp.add(b[0]);
            ans.add(temp);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(s[i]);
        buildTree(arr);
        
        String[] q = sc.nextLine().split(" ");
        int[] queries = new int[q.length];
        for(int i = 0; i < q.length; i++) queries[i] = Integer.parseInt(q[i]);
        
        List<List<Integer>> ans = new ArrayList<>();
        getNodes(queries, root, ans);
        System.out.println(ans);
    }
}