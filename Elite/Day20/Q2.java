import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class Q2{
    public static TreeNode buildTree(int[] levelorder){
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i<levelorder.length){
            TreeNode node = queue.poll();
            if(levelorder[i]!=-1){
                node.left = new TreeNode(levelorder[i]);
                queue.offer(node.left);
            }
            i++;
            if(i<levelorder.length && levelorder[i]!=-1){
                node.right = new TreeNode(levelorder[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    public static void getSum(TreeNode root,int[] currSum){
        if(root == null){
            return;
        }
        getSum(root.right,currSum);
        currSum[0] += root.data;
        root.data = currSum[0];
        getSum(root.left,currSum);
    }
    public static void printSolution(TreeNode root){
        if(root == null){
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n = q.size();
            for(int i = 0;i<n;i++){
                TreeNode top = q.poll();
                System.out.print(top.data + " ");
                if(top.left != null){
                    q.offer(top.left);
                }
                if(top.right != null){
                    q.offer(top.right);
                }
            }
        }
    }
    public static void solve(int[] levelorder){
        TreeNode root = buildTree(levelorder);
        int[] currSum = {0};
        getSum(root,currSum);
        printSolution(root);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] levelorder = new int[input.length];
        for(int i = 0;i<input.length;i++){
            levelorder[i] = Integer.parseInt(input[i]);
        }
        solve(levelorder);
        sc.close();
    }
}