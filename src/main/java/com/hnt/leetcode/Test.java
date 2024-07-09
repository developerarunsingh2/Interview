package com.hnt.leetcode;

public class Test {

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        b.root = new Node(5);
        b.root.left= new Node(4);
        b.root.right = new Node(3);
        b.root.left.left = new Node(7);
        b.root.left.right = new Node(1);

        System.out.println("Length of binanry tree is : "+maxLength(b.root));
    }

    private static int maxLength(Node root) {

        if(root==null){
            return 0;
        }

        else {
            int leftLength = maxLength(root.left);
            int rightLength = maxLength(root.right);


            return 1 + Math.max(leftLength, rightLength);
        }
    }
}
