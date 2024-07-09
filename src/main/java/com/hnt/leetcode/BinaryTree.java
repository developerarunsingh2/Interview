package com.hnt.leetcode;

class BinaryTree {
    Node root;

    // Method to find the depth of the tree
    int maxDepth(Node node) {
        if (node == null)
            return 0;
        else {
            // Compute the depth of the left and right subtrees
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);

            // Use Math.max to find the maximum depth
            return 1 + Math.max(leftDepth, rightDepth);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Create the binary tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);


        System.out.println("Depth of the tree is : " + tree.maxDepth(tree.root));
    }
}