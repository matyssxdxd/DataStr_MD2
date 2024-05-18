package datastr;

public class MyTreeNode<Ttype> {
	//1. variables
	private Ttype element;
	private MyTreeNode parent = null;
	private MyTreeNode rightChild = null;
	private MyTreeNode leftChild = null;
	private int height = 1;
	//2. get and set
	public Ttype getElement() {
		return element;
	}
	public void setElement(Ttype element) {
		if(element!=null)
			this.element = element;
		else
			this.element = (Ttype)new Object();
	}
	public MyTreeNode getParent() {
		return parent;
	}
	public void setParent(MyTreeNode parent) {
		this.parent = parent;
	}
	public MyTreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(MyTreeNode rightChild) {
		this.rightChild = rightChild;
	}
	public MyTreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(MyTreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	//3. constructors
	public MyTreeNode(Ttype element) {
		setElement(element);
	}
	//4. toString
	public String toString() {
		return ""+element;
	}
	
}
