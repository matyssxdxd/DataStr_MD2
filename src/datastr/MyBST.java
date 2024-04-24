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
		
		if(isEmpty())
		{
			root = new MyTreeNode(element);
			counter++;
		}
			
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

	public void print()  throws Exception{
		if(isEmpty()) throw new Exception("Koks ir tukšs");
		
		//izsaukt rekursīvo printešanas funkciju
		printHelpRecursivePreOrder(root);
	}
	
	//TODO izveidot mājās arī PostOrder un InOrder
	private void printHelpRecursivePreOrder(MyTreeNode tempNode) {
		//Root - Left - Right
		
		//ROOT
		System.out.print("P: " +tempNode.getElement());
		
		//LEFT
		//ja eksiste kreisais bērns
		if(tempNode.getLeftChild() != null)
		{
			System.out.print(" -> LC: " 
					+ tempNode.getLeftChild().getElement()
					+ " [" + tempNode.getElement() + "];");
			printHelpRecursivePreOrder(tempNode.getLeftChild());
		}
		
		//RIGHT
		//ja eksistē labais bērns
		if(tempNode.getRightChild() != null)
		{
			System.out.print(" -> RC: " 
					+ tempNode.getRightChild().getElement()
					+ " [" + tempNode.getElement() + "];");
			printHelpRecursivePreOrder(tempNode.getRightChild());
		}
	}
	
	

}
