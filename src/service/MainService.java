package service;

import datastr.MyBST;

public class MainService {

	public static void main(String[] args) {
		MyBST<Integer> bstWithIntegers = new MyBST<Integer>();

		bstWithIntegers.insert(5);
		bstWithIntegers.insert(8);
		bstWithIntegers.insert(3);
		bstWithIntegers.insert(4);
		bstWithIntegers.insert(7);
		bstWithIntegers.insert(6);
		
		try {
			bstWithIntegers.printCute();
			bstWithIntegers.delete(5);
			bstWithIntegers.printCute();
			bstWithIntegers.insert(5);
			bstWithIntegers.printCute();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
