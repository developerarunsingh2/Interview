package com.hnt.leetcode;

public class KthSmallestElementBST {

    public int kthSmallestElement(Node root, int k)
    {
        int result[] = new int[2];
        inordertraversal(root,k,result);

        return result[1];
    }

    public void inordertraversal(Node node, int k, int result[])
    {
        if(node==null)
        {
            return;
        }


            inordertraversal(node.left,k,result);

        //Increment the count of visited nodes
      result[0]++;

      // If the Kth smallest element is found store it and return
        if(result[0]==k)
        {
            result[1]=node.data;
            return;
        }
        inordertraversal(node.right,k,result);

    }
    public static void main(String args[]){
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(6);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.left.left.left = new Node(1);

        // creating an instance of the solution class

        KthSmallestElementBST solution = new KthSmallestElementBST();

        //Testing kth smallest element
        int k=3; // finding the third smallest element
        int kth_smallest = solution.kthSmallestElement(root,k);
        System.out.println("The"+k +"th smallest element in BST is " + kth_smallest);


    }
}
