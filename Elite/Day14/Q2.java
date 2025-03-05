// Cliff Shaw is working on the singly linked list.
// He is given a list of boxes arranged as singly linked list,
// where each box is printed a positive number on it.

// Your task is to help Mr Cliff to find the given list is equivalent to 
// the reverse of it or not. If yes, print "true", otherwise print "false"

// Input Format:
// -------------
// Line-1: space separated integers, boxes as list.

// Output Format:
// --------------
// Print a boolean a value.


// Sample Input-1:
// ---------------
// 3 6 2 6 3

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 6 2 3 6

// Sample Output-2:
// ----------------
// false

import java.util.*;
class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        next = null;
    }
}
public class Q2{
    static Node buildList(int[] nodes, int n){
        if(n == 0) return null;
        Node head = new Node(nodes[0]);
        Node temp = head;
        int i = 1;
        while(i < n){
            temp.next = new Node(nodes[i]);
            temp = temp.next;
            i++;
        }
        return head;
    }
    static boolean isPalindrome(Node head, int n){
        if(head == null) return true;
        Node temp1 = head;
        Node temp2 = head;
        Node temp3 = null;
        int i = 0;
        while(i < n/2){
            temp1 = temp1.next;
            temp2.next = temp3;
            temp3 = temp2;
            temp2 = temp1;
            i++;
        }
        if(n%2 != 0){
            temp1 = temp1.next;
            i++;
        }
        while(i < n){
            if(temp3.data != temp1.data) return false;
            temp1 = temp1.next;
            temp3 = temp3.next;
            i++;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) nodes[i] = Integer.parseInt(s[i]);
        Node head = buildList(nodes, n);
        System.out.println(isPalindrome(head, n));
        sc.close();
    }
}