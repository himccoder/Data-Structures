/*
 * Copyright 2023 Marc Liberatore.
 */
package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of utility methods for trees and their nodes.
 * 
 * You will almost certainly need to add some methods here to complete
 * the unimplemented methods!
 */
public class TreeUtilities {
    /**
     * Perform an in-order traversal of the tree rooted at the given node, and return
     * a list of the elements in the order they were visited.
     * @param node
     * @return a list of elements from the tree from an in-order traversal starting at node
     */
    static <E> List<E> inOrder(Node<E> node) {
        List<E> list = new ArrayList<>();
        inOrderHelper(node,list);
        return list;
    }
    static <E> List<E> inOrderHelper(Node<E> node,List<E> list){
        if (node != null){ 
            inOrderHelper(node.left,list);
            list.add(node.data);
            inOrderHelper(node.right,list);
        }
        return list;
    }

    /**
     * Returns the height of the node n.
     * 
     * null has a height of -1; otherwise, the height is defined as 
     * 1 + the height of the larger of the node's two subtrees.
     * 
     * @param n
     * @return the height of the node n
     */
    static <E> int height(Node<E> n) {
        if (n == null){
            return -1;
        }else{return 1+ Math.max(height(n.left),height(n.right));}
    }

    /**
     * Return a new, balanced tree containing all the values of the old tree bst.
     * @param bst
     * @return a new, balanced tree containing all the values of the old tree bst
     */
    static <E extends Comparable<E>> BinarySearchTree<E> intoBalanced(BinarySearchTree<E> bst) {
        // Convert the binary search tree to a sorted array
    List<E> sortedList = inOrder(bst.root);

    // Build a new, balanced AVL tree from the sorted array
    BinarySearchTree<E> balancedBST = new BinarySearchTree<>();
    buildBalancedAVLTree(sortedList, balancedBST, 0, sortedList.size() - 1);

    return balancedBST;
    }
    static <E extends Comparable<E>> void buildBalancedAVLTree(List<E> sortedList, BinarySearchTree<E> bst, int start, int end) {
        if (start > end) {
            return;
        }
    
        // Find the middle element of the sorted array and add it to the tree
        int mid = (start + end) / 2;
        bst.add(sortedList.get(mid));
    
        // Recursively build the left and right subtrees of the middle element
        buildBalancedAVLTree(sortedList, bst, start, mid - 1);
        buildBalancedAVLTree(sortedList, bst, mid + 1, end);
    }

    /**
     * Returns true iff the tree rooted at n is a Binary Search Tree.
     * 
     * It must have no more than two children per node.
     * 
     * Each node's value must be greater than all the values in its left subtree, and smaller
     * than all the values in its right subtree. (This implies duplicate values are not allowed.)
     * 
     * @param n true iff the tree rooted at n is a Binary Search Tree
     * @return 
     */
    static <E extends Comparable<E>> boolean isBST(Node<E> n) {
        if (n == null) {
            return true;
        }
    
        // check the left subtree
        if (n.left != null) {
            if (n.data.compareTo(n.left.data) <= 0 || !isBST(n.left)) {
                return false;
            }
        }
    
        // check the right subtree
        if (n.right != null) {
            if (n.data.compareTo(n.right.data) >= 0 || !isBST(n.right)) {
                return false;
            }
        }
    
        // if we haven't returned false by now, the tree rooted at n is a binary search tree
        return true;
    }

    /**
     * Returns true iff the tree rooted at n is an AVL tree.
     * 
     * AVL trees are Binary Search trees with the additional property that 
     * every node in the tree has the AVL property.
     * 
     * A node has the AVL property iff the height of its left subtree and the
     * height of its right subtree differ by no more than 1.
     * 
     * @param <E>
     * @param n
     * @returntrue iff the tree rooted at n is an AVL tree
     */
    static <E extends Comparable<E>> boolean isAVLTree(Node<E> n) {
        if (n == null) {
            return true;
        }
    
        // check if the tree is a binary search tree
        if (!isBST(n)) {
            return false;
        }
    
        // check if the AVL property holds for the current node
        int leftHeight = height(n.left);
        int rightHeight = height(n.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        //recurse left adn right nodes
        return isAVLTree(n.left) && isAVLTree(n.right);
    }
    
    /**
     * Returns true iff the subtrees rooted at n and m have the same values 
     * and same structure. 
     * 
     * Only checks child references, not parent references.
     * @param n
     * @param m
     * @return true iff the subtrees rooted at n and m have the same values and same structure
     */
    static <E> boolean equalSubtrees(Node<E> n, Node<E> m) {
        if (n == null && m == null) {
            return true;
        } else if (n == null || m == null) {
            return false;
        } else {
            return n.data.equals(m.data) && equalSubtrees(n.left, m.left) && equalSubtrees(n.right, m.right);
        }
    }
}
