import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {
    private BinarySearchTreeNode<Integer> root;
    private BinarySearchTree<Integer> tree;

    private BinaryTreePrint printer;

    @BeforeEach
    void setUp() {
        root = new BinarySearchTreeNode<>(10);

        root.setLeftChild(new BinarySearchTreeNode<>(7));
        root.setRightChild(new BinarySearchTreeNode<>(12));

        root.getLeftChild().setLeftChild(new BinarySearchTreeNode<>(2));
        root.getLeftChild().setRightChild(new BinarySearchTreeNode<>(8));

        root.getRightChild().setLeftChild(new BinarySearchTreeNode<>(11));
        root.getRightChild().setRightChild(new BinarySearchTreeNode<>(15));

        tree = new BinarySearchTree<>(root);

        printer = new BinaryTreePrint();
    }

    @Test
    void given_BSTree_Where_findMin_Then_ReturnMinElement() {
        assertEquals(2, tree.findMin());
    }

    @Test
    void given_BSTree_Where_findMax_Then_ReturnMaxElement() {
        assertEquals(15, tree.findMax());
    }

    @Test
    void given_ValidNodes_When_TreeIsEmpty_Where_insert_Then_ReturnTrue() {
        var newTree = new BinarySearchTree<Integer>(null);

        newTree.insert(10);

        assertNotNull(newTree.getRoot());
        assertEquals(10, newTree.getRoot().getElement());
    }

    @Test
    void given_ValidNodes_Where_insert_Then_ReturnTrue() {
        var nodes = new int[] {13, 10, 6};
        for (var node: nodes) {
            assertTrue(
                    tree.insert(node)
            );
        }

        printer.printTree(root);

        assertEquals(13, root.getRightChild().getRightChild().getLeftChild().getElement());
        assertEquals(10, root.getLeftChild().getRightChild().getRightChild().getElement());
        assertEquals(6, root.getLeftChild().getLeftChild().getRightChild().getElement());
    }

    @Test
    void given_LeafElement_Where_removeElement_Then_ReturnTrue() {
        boolean result = tree.removeElement(2);
        printer.printTree(root);

        assertTrue(result);

        assertFalse(tree.contains(2));
        assertNull(root.getLeftChild().getLeftChild());
    }

    @Test
    void given_NodeWith2Children_Where_removeElement_Then_ReturnTrue() {
        boolean result = tree.removeElement(7);
        printer.printTree(root);

        assertTrue(result);

        assertFalse(tree.contains(7));
        assertEquals(8, root.getLeftChild().getElement());
        assertEquals(2, root.getLeftChild().getLeftChild().getElement());
        assertNull(root.getLeftChild().getRightChild());
    }

    @Test
    void given_NonExistingElement_Where_removeElement_Then_ReturnFalse() {
        boolean result = tree.removeElement(4);
        printer.printTree(root);

        assertFalse(result);

        assertFalse(tree.contains(4));
    }

    @Test
    void given_UnbalancedBSTree_Where_reBalance_Then_BalanceTheTree() {
        // arrange
        tree.insert(3);
        tree.insert(4);

        tree.insert(16);
        tree.insert(17);
        tree.insert(18);

        printer.printTree(tree.getRoot());

        // act
        tree.reBalance();

        printer.printTree(tree.getRoot());

        // assert
        var preOrder = new Integer[] {10, 4, 2, 3, 7, 8, 15, 11, 12, 17, 16, 18};

        assertArrayEquals(preOrder, tree.preOrder().toArray());
        assertEquals(3, tree.height());
    }
}
