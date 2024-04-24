package datastr;

public class MyBST<Ttype> {
	private MyTreeNode root = null;
	private int counter = 0;
	
	//pēc noklusējuma jau būs bezargumenta konstruktors
	
	public boolean isEmpty() {
		return (counter==0);
	}
	
	public int howManyElements() {
		return counter;
	}
	
	public void insert(Ttype element) {
		
		if(isEmpty()) root = new MyTreeNode(element);
		
		else
		{
			//izsaukt rekursīvo funkciju pirmo reizi
			insertHelpRecursive(root  ,element);
		}
	
	}
	
	private void insertHelpRecursive(MyTreeNode tempNode,
			Ttype element) {
		//apakškoka sakne ir lielāks par elementu
		if(
		((Comparable)tempNode.getElement()).compareTo(element) == 1)
		{
			//kreisais bērns neeksistē
			if(tempNode.getLeftChild() == null)
			{
				MyTreeNode newNode = new MyTreeNode(element);
				tempNode.setLeftChild(newNode);
				newNode.setParent(tempNode);
				counter++;
			}//kreisais bērns eksistē
			else
			{
				insertHelpRecursive(tempNode.getLeftChild(), element);
			}
		}
		//saknes elements ir mazaks par element
		else if(
		((Comparable)tempNode.getElement()).compareTo(element) == -1) {
			//labais bērns neeksistē
			if(tempNode.getRightChild() == null) {
				MyTreeNode newNode = new MyTreeNode(element);
				tempNode.setRightChild(newNode);
				newNode.setParent(tempNode);
				counter++;
			}
			//labais berns jau eksistē
			else
			{
				insertHelpRecursive(tempNode.getRightChild(), element);
			}
		}
		//šāds elements jau eksistē
		else
		{
			System.out.println("Šāds elements jau eksistē BST");
		}
			
		
		
	}
	

}
