import java.util.ArrayList;

public class BinaryTree<T> {
    private BinaryTreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> root) {
        if (root == null)
            return 0;

        return size(root.getLeftChild()) + 1 + size(root.getRightChild());
    }

    public boolean contains(T element) {
        return containsRecursive(root, element);
    }

    private boolean containsRecursive(BinaryTreeNode<T> root, T element) {
        if (root == null)
            return false;

        if (root.getElement().equals(element))
            return true;

        //left
        if( containsRecursive(root.getLeftChild(), element) )
            return true;

        // right
        return containsRecursive(root.getRightChild(), element);
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> elements = new ArrayList<>();
        inOrderRecursive(root, elements);

        return elements;
    }

    private void inOrderRecursive(BinaryTreeNode<T> root, ArrayList<T> elements) {
        if (root == null)
            return;

        inOrderRecursive(root.getLeftChild(), elements);
        elements.add(root.getElement());
        inOrderRecursive(root.getRightChild(), elements);
    }

    public ArrayList<T> preOrder() {
        ArrayList<T> elements = new ArrayList<>();
        preOrderRecursive(root, elements);

        return elements;
    }

    private void preOrderRecursive(BinaryTreeNode<T> root, ArrayList<T> elements) {
        if (root == null)
            return;

        elements.add(root.getElement());
        preOrderRecursive(root.getLeftChild(), elements);
        preOrderRecursive(root.getRightChild(), elements);
    }

    public ArrayList<T> postOrder() {
        ArrayList<T> elements = new ArrayList<>();
        postOrderRecursive(root, elements);

        return elements;
    }

    private void postOrderRecursive(BinaryTreeNode<T> root, ArrayList<T> elements) {
        if (root == null)
            return;

        postOrderRecursive(root.getLeftChild(), elements);
        postOrderRecursive(root.getRightChild(), elements);
        elements.add(root.getElement());
    }

    public ArrayList<T> levelOrder() {
        ArrayList<T> elements = new ArrayList<>();
        for (int i = 0; i <= height() + 1; i++) {
            addCurrentLevel(root, i, elements);
        }

        return elements;
    }

    private void addCurrentLevel(BinaryTreeNode<T> root, int level, ArrayList<T> elements) {
        if (root == null)
            return;

        if (level == 1) {
            elements.add(root.getElement());
            return;
        }

        if (level > 1) {
            addCurrentLevel(root.getLeftChild(), level - 1, elements);
            addCurrentLevel(root.getRightChild(), level - 1, elements);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(BinaryTreeNode<T> node) {
        if (node == null)
            return -1;

        int leftHeight= height(node.getLeftChild());
        int rightHeight = height(node.getRightChild());

        return Math.max(leftHeight, rightHeight) + 1;
    }

}
