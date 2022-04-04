import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {
    private BinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {

        var root = new BinaryTreeNode<>(1);
        root.setLeftChild(new BinaryTreeNode<>(2));
        root.setRightChild(new BinaryTreeNode<>(3));

        root.getLeftChild().setLeftChild(new BinaryTreeNode<>(4));
        root.getLeftChild().setRightChild(new BinaryTreeNode<>(5));

        tree = new BinaryTree<>(root);
    }

    @Test
    void given_NotEmptyTree_Where_isEmpty_Then_ReturnFalse() {
        assertFalse(tree.isEmpty());
    }

    @Test
    void given_EmptyTree_Where_isEmpty_Then_ReturnTrue() {
        var emptyTree = new BinaryTree<>();
        assertTrue(emptyTree.isEmpty());
    }


    @Test
    void given_ExistingNodes_Where_contains_Then_ReturnTrue() {
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
    }

    @Test
    void given_NonExistingNode_Where_contains_Then_ReturnFalse() {
        assertFalse(tree.contains(8));
        assertFalse(tree.contains(10));
        assertFalse(tree.contains(100));
    }

    @Test
    void given_Nodes_Where_inOrder_Then_ReturnInOrderNodesList() {
        var expected = new Integer[] {4, 2, 5, 1, 3};

        var ordered = tree.inOrder();

        assertArrayEquals(expected, ordered.toArray());
    }

    @Test
    void given_Nodes_Where_preOrder_Then_ReturnPreOrderNodesList() {
        var expected = new Integer[] {1, 2, 4, 5, 3};

        var ordered = tree.preOrder();

        assertArrayEquals(expected, ordered.toArray());
    }

    @Test
    void given_Nodes_Where_postOrder_Then_ReturnPostOrderNodesList() {
        var expected = new Integer[] {4, 5, 2, 3, 1};

        var ordered = tree.postOrder();

        assertArrayEquals(expected, ordered.toArray());
    }

    @Test
    void given_Nodes_Where_levelOrder_Then_ReturnLevelOrderNodesList() {
        var expected = new Integer[] {1, 2, 3, 4, 5};

        var ordered = tree.levelOrder();

        assertArrayEquals(expected, ordered.toArray());
    }

    @Test
    void given_TreeWithDepthOf2_Where_height_Then_Return2() {
        assertEquals(2, tree.height());
    }
}
