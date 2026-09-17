package datastructures.bst;

public class BST<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left;
        Node right;

        Node(T value) {
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root = null;
    private int size = 0;

    public void insert(T value) {
        Node newNode = new Node(value);

        // if BST was empty
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node currentNode = root;
        while (true) {
            int compare = value.compareTo(currentNode.data);
            if (compare == 0) { // compareTo() method returns 0 if value = currentNode.data
                return;
            } else if (compare < 0) { // compareTo() method returns < 0 if value < currentNode.data
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    size++;
                    break;
                }
                currentNode = currentNode.left;
            } else if (compare > 0) { // compareTo() method returns > 0 if value > currentNode.data
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    size++;
                    break;
                }
                currentNode = currentNode.right;
            }
        }
    }

    public boolean contains(T value) {
        if (root == null) {
            return false;
        }

        Node currentNode = root;

        while (currentNode != null) {
            int compare = value.compareTo(currentNode.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                currentNode = currentNode.left;
            } else if (compare > 0) {
                currentNode = currentNode.right;
            }
        }

        return false;
    }

    private T findMin(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    private Node remove(Node node, T value) {
        if (node == null) {
            return null;
        }
        int compare = value.compareTo(node.data);

        if (compare > 0) {
            node.right = remove(node.right, value);
        } else if (compare < 0) {
            node.left = remove(node.left, value);
        } else {
            if (node.left == null && node.right == null) {
                size--;
                return null;
            }

            if (node.left != null && node.right == null) {
                size--;
                return node.left;
            }

            if (node.right != null && node.left == null) {
                size--;
                return node.right;
            }

            if (node.right != null && node.left != null) {
                // we will find successor and replace it with the current node that to be deleted
                // find min of right subtree
                T successor = findMin(node.right);
                node.right = remove(node.right, successor);
                node.data = successor;
            }
        }
        return node;
    }

    private void inorderTraverse(Node node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        System.out.print(node.data + " ");
        inorderTraverse(node.right);
    }

    public T findMin() {
        return findMin(root);
    }

    public void remove(T value) {
        root = remove(root, value);
    }

    public void inorderTraverse() {
        inorderTraverse(root);
        System.out.println();
    }

    public void size() {
        System.out.println(size);
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(5);
        tree.insert(10);
        tree.insert(8);
        tree.insert(9);
        tree.insert(13);
        tree.insert(11);
        tree.insert(12);

        tree.inorderTraverse();

        // removing a leaf node
        tree.remove(12);
        tree.size();

        System.out.println(tree.findMin());

        // removing a node with two childrens
        tree.remove(10);
        tree.size();

        tree.inorderTraverse();

        System.out.println(tree.contains(5));
        System.out.println(tree.contains(10));


    }
}