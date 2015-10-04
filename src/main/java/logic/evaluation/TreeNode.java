package logic.evaluation;

public class TreeNode {

    private TreeValueType type;
    private String value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String value, TreeValueType type) {
        this.value = value;
        this.type = type;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public String getValue() {
        return value;
    }

    public TreeValueType getType() {
        return type;
    }

}
