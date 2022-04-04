import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T> > extends BinaryTree<T> {
    private int size;

    public BinarySearchTree(BinarySearchTreeNode<T> root) {
        super(root);
    }

    public boolean insert(T element) {
        super.setRoot(
                insert(element, (BinarySearchTreeNode<T>) super.getRoot())
        );

        this.size++;
        return true;
    }

    private BinarySearchTreeNode<T> insert(T element, BinarySearchTreeNode<T> root) {
        if (root == null) {
            return new BinarySearchTreeNode<>(element);
        }

        int result = root.compareTo(element);

        if (result >= 0) {
            root.setLeftChild(
                insert(element, (BinarySearchTreeNode<T>) root.getLeftChild())
            );
        }

        else {

             root.setRightChild(
                     insert(element, (BinarySearchTreeNode<T>) root.getRightChild())
             );
        }
        return root;
    }

    public boolean removeElement(T element) {
        if (!contains(element)) {
            return false;
        }

        var root = removeElement(element, super.getRoot());
        super.setRoot(root);
        this.size--;
        return true;
    }

    private BinaryTreeNode<T> removeElement(T element, BinaryTreeNode<T> root) {
        if (root == null)
            return null;

        int result = root.getElement().compareTo(element);
        if (result > 0) {
            root.setLeftChild(
                    removeElement(element, root.getLeftChild())
            );
        }
        else if (result < 0) {
            root.setRightChild(
                    removeElement(element, root.getRightChild())
            );
        }

        // found the node
        else {
            if (root.getLeftChild() == null)
                return root.getRightChild();

            else if (root.getRightChild() == null)
                return root.getLeftChild();

            root.setElement(findMin((BinarySearchTreeNode<T>) root.getRightChild()));

            root.setRightChild(
                    removeElement(root.getElement(), root.getRightChild())
            );
        }
        return root;
    }

    public T findMin() {
        if (super.getRoot() == null) {
            return null;
        }

        return findMin((BinarySearchTreeNode<T>) super.getRoot());
    }

    private T findMin(BinarySearchTreeNode<T> root) {
        if (root.getLeftChild() == null) {
            return root.getElement();
        }

        return findMin((BinarySearchTreeNode<T>) root.getLeftChild());
    }


    public T findMax() {
        if (super.getRoot() == null) {
            return null;
        }

        return findMax((BinarySearchTreeNode<T>) super.getRoot());
    }

    private T findMax(BinarySearchTreeNode<T> root) {
        if (root.getRightChild() == null) {
            return root.getElement();
        }

        return findMax((BinarySearchTreeNode<T>) root.getRightChild());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T element) {
        BinarySearchTreeNode<T> root = (BinarySearchTreeNode<T>) super.getRoot();

        while(root != null)
        {
            if(root.getElement().equals(element))
                return true;

            int result = root.compareTo(element);
            if(result >= 0)  {
                root = (BinarySearchTreeNode<T>) root.getLeftChild();
            }
            else {
                root = (BinarySearchTreeNode<T>) root.getRightChild();
            }
        }

        return false;
    }


    public void reBalance() {
        var sorted = super.inOrder();
        int size = super.size();

        var newRoot = reBalance(sorted, 0, size-1);
        super.setRoot(newRoot);
    }

    private BinarySearchTreeNode<T> reBalance(ArrayList<T> elements, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        var element = elements.get(mid);

        var node = new BinarySearchTreeNode<>(element);

        node.setLeftChild(
                reBalance(elements, start, mid - 1)
        );
        node.setRightChild(
                reBalance(elements, mid + 1, end)
        );

        return node;
    }
}
