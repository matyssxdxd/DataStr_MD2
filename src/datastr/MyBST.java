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

		root = insertHelpRecursive(root, element);
		counter++;

	}

	private MyTreeNode<Ttype> insertHelpRecursive(MyTreeNode<Ttype> tempNode, Ttype element) {
		// nav elementu
		if (tempNode == null) {
			return new MyTreeNode<>(element);
		}
		// apakškoka sakne ir lielāks par elementu
		if (((Comparable<Ttype>) tempNode.getElement()).compareTo(element) > 0) {
			tempNode.setLeftChild(insertHelpRecursive(tempNode.getLeftChild(), element));
			tempNode.getLeftChild().setParent(tempNode);
		}
		// saknes elements ir mazāks par elementu
		else if (((Comparable<Ttype>) tempNode.getElement()).compareTo(element) < 0) {
			tempNode.setRightChild(insertHelpRecursive(tempNode.getRightChild(), element));
			tempNode.getRightChild().setParent(tempNode);
		}
		// šāds elements jau eksistē
		else {
			return tempNode;
		}

		tempNode.setHeight(Math.max(height(tempNode.getLeftChild()), height(tempNode.getRightChild())) + 1);

		int balanceFactor = getBalanceFactor(tempNode);

		// Left Left Case
		if (balanceFactor > 1 && ((Comparable<Ttype>) tempNode.getLeftChild().getElement()).compareTo(element) > 0) {
			return rightRotate(tempNode);
		}

		// Right Right Case
		if (balanceFactor < -1 && ((Comparable<Ttype>) tempNode.getRightChild().getElement()).compareTo(element) < 0) {
			return leftRotate(tempNode);
		}

		// Left Right Case
		if (balanceFactor > 1 && ((Comparable<Ttype>) tempNode.getLeftChild().getElement()).compareTo(element) < 0) {
			tempNode.setLeftChild(leftRotate(tempNode.getLeftChild()));
			tempNode.getLeftChild().setParent(tempNode);
			return rightRotate(tempNode);
		}

		// Right Left Case
		if (balanceFactor < -1 && ((Comparable<Ttype>) tempNode.getRightChild().getElement()).compareTo(element) > 0) {
			tempNode.setRightChild(rightRotate(tempNode.getRightChild()));
			tempNode.getRightChild().setParent(tempNode);
			return leftRotate(tempNode);
		}

		return tempNode;
	}


	public void print() throws Exception {
		if (isEmpty())
			throw new Exception("Koks ir tukšs");

		// izsaukt rekursīvo printešanas funkciju
		printHelpRecursivePreOrder(root);
	}

	// TODO izveidot mājās arī PostOrder un InOrder
	private void printHelpRecursivePreOrder(MyTreeNode tempNode) {
		System.out.print("P: " + tempNode.getElement());
		if (tempNode.getLeftChild() != null) {
			System.out.print(" -> LC: " + tempNode.getLeftChild().getElement() + " [" + tempNode.getElement() + "];");
			printHelpRecursivePreOrder(tempNode.getLeftChild());
		}
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
		root = deleteHelpRecursive(root, element);
		
	}
	
	private MyTreeNode<Ttype> deleteHelpRecursive(MyTreeNode tempNode, Ttype element ) {
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
			//3. džēšajamam mezglam ir abi bērni
			else if(tempNode.getLeftChild()!=null
					&& tempNode.getRightChild()!=null)
			{
				//iet pa labo pusi un sameklē kreisāko bērnu
				//TODO vai eksistē labias bērns root elementam
				MyTreeNode temp2 = root.getRightChild();
				while(temp2.getLeftChild()!=null)
				{
					temp2 = temp2.getLeftChild();
				}
				//temp2 - būs ar to vērtību, kas ir jāieliek ieks tempNode
				tempNode.setElement(temp2.getElement());
				
				MyTreeNode parent = temp2.getParent();
				if(parent.getLeftChild().equals(temp2))
				{
					parent.setLeftChild(null);
				}
				else if (parent.getRightChild().equals(temp2))
				{
					parent.setRightChild(null);
				}
				
				
			}
				
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

		tempNode.setHeight(Math.max(height(tempNode.getLeftChild()), height(tempNode.getRightChild())) + 1);
		int balanceFactor = getBalanceFactor(tempNode);
		if (balanceFactor > 1) {
			if (getBalanceFactor(tempNode.getLeftChild()) >= 0) {
				return rightRotate(tempNode);
			} else {
				tempNode.setLeftChild(leftRotate(tempNode.getLeftChild()));
				return rightRotate(tempNode);
			}
		}
		if (balanceFactor < -1) {
			if (getBalanceFactor(tempNode.getRightChild()) <= 0) {
				return leftRotate(tempNode);
			} else {
				tempNode.setRightChild(rightRotate(tempNode.getRightChild()));
				return leftRotate(tempNode);
			}
		}
		return tempNode;
		

	}
	
	private int height(MyTreeNode<Ttype> node) {
		if (node == null)
			return 0;
		return node.getHeight();
	}

	private MyTreeNode<Ttype> rightRotate(MyTreeNode<Ttype> y) {
		MyTreeNode<Ttype> x = y.getLeftChild();
		MyTreeNode<Ttype> T2 = x.getRightChild();

		x.setRightChild(y);
		y.setLeftChild(T2);

		x.setParent(y.getParent());
		y.setParent(x);
		if (T2 != null) {
			T2.setParent(y);
		}

		y.setHeight(Math.max(height(y.getLeftChild()), height(y.getRightChild())) + 1);
		x.setHeight(Math.max(height(x.getLeftChild()), height(x.getRightChild())) + 1);

		return x;
	}

	private MyTreeNode<Ttype> leftRotate(MyTreeNode<Ttype> x) {
		MyTreeNode<Ttype> y = x.getRightChild();
		MyTreeNode<Ttype> T2 = y.getLeftChild();

		y.setLeftChild(x);
		x.setRightChild(T2);

		y.setParent(x.getParent());
		x.setParent(y);
		if (T2 != null) {
			T2.setParent(x);
		}

		x.setHeight(Math.max(height(x.getLeftChild()), height(x.getRightChild())) + 1);
		y.setHeight(Math.max(height(y.getLeftChild()), height(y.getRightChild())) + 1);

		return y;
	}


	public int getBalanceFactor(MyTreeNode<Ttype> node) {
		if (node == null)
			return 0;
		return height(node.getLeftChild()) - height(node.getRightChild());
	}


	// https://www.programiz.com/dsa/avl-tree
	private void printTree(MyTreeNode<Ttype> currPtr, String indent, boolean last) {
		if (currPtr != null) {
			System.out.print(indent);
			if (last) {
				System.out.print("R----");
				indent += "   ";
			} else {
				System.out.print("L----");
				indent += "|  ";
			}
			System.out.println(currPtr.getElement());
			printTree(currPtr.getLeftChild(), indent, false);
			printTree(currPtr.getRightChild(), indent, true);
		}
	}

	public void printCute() {
		printTree(root, "", true);

	}
}
