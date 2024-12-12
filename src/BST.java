import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Eisha Yadav
 * @version: 12/10/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    //Search function
    public boolean search(int val) {
        // TODO: Complete the search function
        //Use Helper Function that accepts the initial root as a parameter, allowing for recursion
        return search(val, getRoot());

    }
    //Search Helper Function, accepting more parameters
    public boolean search(int val, BSTNode node){
        //Base Case: If val, return true
        if (node.getVal() == val) {
            return true;
        }
        //Base case: If value is null, return false (reached end of tree- value was not found)
        if (node.getLeft() == null || node.getRight() == null) {
            return false;
        }
        //If not, go to each child and recurse
        return (search(val, node.getLeft()) || search(val, node.getRight()));
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    //Inorder traversal of tree, in order of left, root, right
    public ArrayList<BSTNode> getInorder() {
        // TODO: Complete inorder traversal
        //Returns nodes in order of left, root, right
        //Create New Arraylist
        ArrayList<BSTNode> inOrder = new ArrayList<BSTNode>();
        //Call helper function
        return getInorder(getRoot(), inOrder);
    }
    //Inorder Traversal Helper Function
    public ArrayList<BSTNode> getInorder(BSTNode node, ArrayList<BSTNode> inOrder){
        //Returns when node is null
        if(node == null) {
            return inOrder;
        }
        getInorder(node.getLeft(), inOrder);
        inOrder.add(node);
        getInorder(node.getRight(), inOrder);
        return inOrder;
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    //Preorder Traversal of Tree
    public ArrayList<BSTNode> getPreorder() {
        // TODO: Complete preorder traversal
        //Returns nodes in order of root, left, right
        //Create New Arraylist
        ArrayList<BSTNode> preOrder = new ArrayList<BSTNode>();
        return getPreorder(getRoot(), preOrder);
    }
    //Helper Function for Preorder Traversal
    public ArrayList<BSTNode> getPreorder(BSTNode node, ArrayList<BSTNode> preOrder){
        if(node == null) {
            return preOrder;
        }
        preOrder.add(node);
        getPreorder(node.getLeft(), preOrder);
        getPreorder(node.getRight(), preOrder);
        return preOrder;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    //Postorder Traversal
    public ArrayList<BSTNode> getPostorder() {
        // TODO: Complete postorder traversal
        //Returns nodes in order of left, right, root
        //Create New Arraylist
        ArrayList<BSTNode> postOrder = new ArrayList<BSTNode>();
        return getPostorder(getRoot(), postOrder);
    }
    //Helper Function for Postorder Traversal
    public ArrayList<BSTNode> getPostorder(BSTNode node, ArrayList<BSTNode> postOrder){
        if(node == null) {
            return postOrder;
        }
        getPostorder(node.getLeft(), postOrder);
        getPostorder(node.getRight(), postOrder);
        postOrder.add(node);
        return postOrder;
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    //Inserts value into Tree
    public void insert(int val) {
        // TODO: Complete insert
        if (!search(val)) {
            insert(val, getRoot());
        }
    }
    //Helper Function for Insert
    public void insert(int val, BSTNode node){
        //Insert value to the right if value is greater than value of the node
        if (node.getVal() < val){
            //Insert if there is no value at the node
            if (node.getRight() == null) {
                node.setRight(new BSTNode(val));
            }
            //Recurse if node has a value until null node is found
            insert(val, node.getRight());
        }
        //Insert value to the left if value is less than the value of the node
        if(node.getVal() > val){
            //Insert at node if node is null
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode(val));
            }
            //Recurse if node has a value until null node is found
            insert(val, node.getLeft());
        }
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        //Call Helper Function
        return isValidBST(getRoot());

    }

    public boolean isValidBST(BSTNode node) {
        if(node.getRight() == null || node.getLeft() == null || node == null){
            return true;
        }
        if (node.getRight().getVal() < node.getVal() || node.getLeft().getVal() > node.getVal()){
            return false;
        }

        return isValidBST(node.getLeft()) && isValidBST(node.getRight());

    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
