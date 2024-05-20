package datastr;

public class MyStackNode {
    public MyBETreeNode treeNode;
    public MyStackNode next = null;

    public MyStackNode(MyBETreeNode treeNode) {
        this.treeNode = treeNode;
    }
}
