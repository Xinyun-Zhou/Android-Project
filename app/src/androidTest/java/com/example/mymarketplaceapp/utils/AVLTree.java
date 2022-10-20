package com.example.mymarketplaceapp.utils;

/**
 * AVL tree
 *
 * @param <T>
 * @author lab code, u7366711 Yuxuan Zhao
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /*
        As a result of inheritance by using 'extends BinarySearchTree<T>,
        all class fields within BinarySearchTree are also present here.
        So while not explicitly written here, this class has:
            - value
            - leftNode
            - rightNode
     */

    public AVLTree(T value) {
        super(value);
        // Set left and right children to be of EmptyAVL as opposed to EmptyBST.
        this.leftNode = new EmptyAVL<>();
        this.rightNode = new EmptyAVL<>();
    }

    public AVLTree(T value, Tree<T> leftNode, Tree<T> rightNode) {
        super(value, leftNode, rightNode);
    }

    /**
     * @return balance factor of the current node.
     */
    public int getBalanceFactor() {
        /*
             Note:
             Calculating the balance factor and height each time they are needed is less efficient than
             simply storing the height and balance factor as fields within each tree node (as some
             implementations of the AVLTree do). However, although it is inefficient, it is easier to implement.
         */
        return leftNode.getHeight() - rightNode.getHeight();
    }

    /**
     * Insert an element
     *
     * @param element
     * @return the new AVL tree after insertion
     * @author u7366711 Yuxuan Zhao
     */
    @Override
    public AVLTree<T> insert(T element) {
        // Ensure input is not null.
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");
        // Right
        if (element.compareTo(value) > 0) {
            AVLTree<T> newAVLTree = new AVLTree<>(value, leftNode, rightNode.insert(element));
            // No Rotation
            if (Math.abs(newAVLTree.getBalanceFactor()) <= 1)
                return newAVLTree;
            else {
                if (newAVLTree.rightNode.rightNode.getHeight() > newAVLTree.rightNode.leftNode.getHeight()) //Type RR
                    return newAVLTree.leftRotate();
                else { //Type RL
                    AVLTree<T> newRightTree = (AVLTree<T>) newAVLTree.rightNode;
                    newAVLTree.rightNode = newRightTree.rightRotate();

                    return newAVLTree.leftRotate();
                }

            }
            // Left
        } else if (element.compareTo(value) < 0) {
            AVLTree<T> newAVLTree = new AVLTree<>(value, leftNode.insert(element), rightNode);
            // No Rotation
            if (Math.abs(newAVLTree.getBalanceFactor()) <= 1)
                return newAVLTree;
            else {
                if (newAVLTree.leftNode.leftNode.getHeight() > newAVLTree.leftNode.rightNode.getHeight()) //Type LL
                    return newAVLTree.rightRotate();
                else { //Type LR
                    AVLTree<T> newLeftTree = (AVLTree<T>) newAVLTree.leftNode;
                    newAVLTree.leftNode = newLeftTree.leftRotate();

                    return newAVLTree.rightRotate();
                }
            }

        } else {
            return this;
        }
    }

    /**
     * Conducts a left rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     * @author u7366711 Yuxuan Zhao
     */
    public AVLTree<T> leftRotate() {
        Tree<T> newParent = this.rightNode;
        Tree<T> newRightOfCurrent = newParent.leftNode;

        newParent.leftNode = this;
        this.rightNode = newRightOfCurrent;

        return (AVLTree<T>) newParent;
    }

    /**
     * Conducts a right rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     * @author u7366711 Yuxuan Zhao
     */
    public AVLTree<T> rightRotate() {
        Tree<T> newParent = this.leftNode;
        Tree<T> newLeftOfCurrent = newParent.rightNode;

        newParent.rightNode = this;
        this.leftNode = newLeftOfCurrent;

        return (AVLTree<T>) newParent;
    }

    /**
     * Note that this is not within a file of its own... WHY?
     * The answer is: this is just a design decision. 'insert' here will return something specific
     * to the parent class inheriting Tree from BinarySearchTree. In this case an AVL tree.
     */
    public static class EmptyAVL<T extends Comparable<T>> extends EmptyTree<T> {
        @Override
        public Tree<T> insert(T element) {
            // The creation of a new Tree, hence, return tree.
            return new AVLTree<T>(element);
        }
    }
}
