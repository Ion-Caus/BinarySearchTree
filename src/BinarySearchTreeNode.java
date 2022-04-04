public class BinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> implements Comparable<T> {

    public BinarySearchTreeNode(T element) {
        super(element);
    }

    @Override
    public int compareTo(T o) {
        return super.getElement().compareTo(o);
    }
}
