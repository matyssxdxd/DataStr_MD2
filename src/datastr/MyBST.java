package datastr;

public class MyBST<Ttype> {
	private MyTreeNode root = null;
	private int counter = 0;

	// pēc noklusējuma jau būs bezargumenta konstruktors

	public boolean isEmpty() {
		return (counter == 0);
	}

	public int howManyElements() {
		return counter;
	}

	public void insert(Ttype element) {

		if (isEmpty()) {
			root = new MyTreeNode(element);
			counter++;
		}

		else {
			// izsaukt rekursīvo funkciju pirmo reizi
			insertHelpRecursive(root, element);
		}

	}

	private void insertHelpRecursive(MyTreeNode tempNode, Ttype element) {
		// apakškoka sakne ir lielāks par elementu
		if (((Comparable) tempNode.getElement()).compareTo(element) == 1) {
			// kreisais bērns neeksistē
			if (tempNode.getLeftChild() == null) {
				MyTreeNode newNode = new MyTreeNode(element);
				tempNode.setLeftChild(newNode);
				newNode.setParent(tempNode);
				counter++;
			} // kreisais bērns eksistē
			else {
				insertHelpRecursive(tempNode.getLeftChild(), element);
			}
		}
		// saknes elements ir mazaks par element
		else if (((Comparable) tempNode.getElement()).compareTo(element) == -1) {
			// labais bērns neeksistē
			if (tempNode.getRightChild() == null) {
				MyTreeNode newNode = new MyTreeNode(element);
				tempNode.setRightChild(newNode);
				newNode.setParent(tempNode);
				counter++;
			}
			// labais berns jau eksistē
			else {
				insertHelpRecursive(tempNode.getRightChild(), element);
			}
		}
		// šāds elements jau eksistē
		else {
			System.out.println("Šāds elements jau eksistē BST");
		}
	}

	public void print() throws Exception {
		if (isEmpty())
			throw new Exception("Koks ir tukšs");

		// izsaukt rekursīvo printešanas funkciju
		printHelpRecursivePreOrder(root);
	}

	// TODO izveidot mājās arī PostOrder un InOrder
	private void printHelpRecursivePreOrder(MyTreeNode tempNode) {
		// Root - Left - Right

		// ROOT
		System.out.print("P: " + tempNode.getElement());

		// LEFT
		// ja eksiste kreisais bērns
		if (tempNode.getLeftChild() != null) {
			System.out.print(" -> LC: " + tempNode.getLeftChild().getElement() + " [" + tempNode.getElement() + "];");
			printHelpRecursivePreOrder(tempNode.getLeftChild());
		}

		// RIGHT
		// ja eksistē labais bērns
		if (tempNode.getRightChild() != null) {
			System.out.print(" -> RC: " + tempNode.getRightChild().getElement() + " [" + tempNode.getElement() + "];");
			printHelpRecursivePreOrder(tempNode.getRightChild());
		}
	}

	// TODO
	// izveiot search funkcijas deklaracijau
	public boolean search(Ttype element) throws Exception {
		// pārbaude vai nav tukšs
		if (isEmpty())
			throw new Exception("Koks ir tukšs un nevar veikt meklēšanu");

		return searchHelpRecursive(root, element);
	}

	private boolean searchHelpRecursive(MyTreeNode tempNode, Ttype element) {
		if (tempNode.getElement().equals(element))
			return true;
		// apakškoka sakne ir lielāks par elementu
		if (
	((Comparable) tempNode.getElement()).compareTo(element) == 1) {
			//ja kreisais bērns eksistē
			if (tempNode.getLeftChild() != null) 
			{
				return searchHelpRecursive(tempNode.getLeftChild(), element);
			}
		}
		// saknes elements ir mazaks par element
		else if (
	((Comparable) tempNode.getElement()).compareTo(element) == -1) {
			//ja labais bērns eksistē
			if (tempNode.getRightChild() != null) 
			{
				return searchHelpRecursive(tempNode.getRightChild(), element);
			}
		}
		
		return false;

	}
	
	
	public void delete(Ttype element) throws Exception{
		if (isEmpty())
			throw new Exception("Koks ir tukšs un nevar veikt dzēšanu");

		//TODO pārliecināties, ka ir tikai root!
		//izsaukt rekurtsīvo dzēšanas funkciju
		deleteHelpRecursive(root, element);
		
	}
	
	private void deleteHelpRecursive(MyTreeNode tempNode, Ttype element ) {
		//esam nonakusi līdz elementam, kuri gribam dzēst
		if (tempNode.getElement().equals(element))
		{
			//1. dzēšamais mezgls ir lapa
			if(tempNode.getLeftChild()==null
					&& tempNode.getRightChild()==null)
			{
				MyTreeNode parent = tempNode.getParent();
				//no vecaka puses noskaidroju, ka sis ir kreisais bērns
				if(parent.getLeftChild().getElement().equals(element))
					parent.setLeftChild(null);
				
				//no vecaka puses noskaidroju, ka sis ir labais bērns
				else if(parent.getRightChild().getElement().equals(element))
					parent.setRightChild(null);
				
			}
			
			//2. dzešamais mezgls ir ar vienu bērnu
			//2.1.ir tikai kreisais bērns
			else if(tempNode.getLeftChild()!=null
					&& tempNode.getRightChild()==null)
			{
				MyTreeNode parent = tempNode.getParent();
				MyTreeNode leftChild = tempNode.getLeftChild();
				parent.setLeftChild(leftChild);
				leftChild.setParent(parent);
			}
			//2.2.ir tikai labais bērns
			else if(tempNode.getLeftChild()==null
					&& tempNode.getRightChild()!=null)
			{
				MyTreeNode parent = tempNode.getParent();
				MyTreeNode rightChild = tempNode.getRightChild();
				parent.setRightChild(rightChild);
				rightChild.setParent(parent);
			}
			else if(tempNode.getLeftChild()!=null
					&& tempNode.getRightChild()!=null)
			{
				//iet pa labo pusi un sameklē kreisāko bērnu
				//TODO vai eksistē labias bērns root elementam
				MyTreeNode temp2 = root.getRightChild();
				while(temp2!=null)
				{
					temp2 = temp2.getLeftChild();
				}
				//temp2 - būs ar to vērtību, kas ir jāieliek ieks tempNode
				
			}
			
			
			//3. džēšajama mezgls ir abi bērni
		}
		// apakškoka sakne ir lielāks par elementu
		if (
	((Comparable) tempNode.getElement()).compareTo(element) == 1) {
			//ja kreisais bērns eksistē
			if (tempNode.getLeftChild() != null) 
			{
				deleteHelpRecursive(tempNode.getLeftChild(), element);
			}
		}
		// saknes elements ir mazaks par element
		else if (
	((Comparable) tempNode.getElement()).compareTo(element) == -1) {
			//ja labais bērns eksistē
			if (tempNode.getRightChild() != null) 
			{
				deleteHelpRecursive(tempNode.getRightChild(), element);
			}
		}
		

	}
	
	
	

}
