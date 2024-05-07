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
			bstWithIntegers.print();
			System.out.println();
			System.out.println("3? -> " + bstWithIntegers.search(3));//true
			System.out.println("100? -> " + bstWithIntegers.search(100));//false
			
			bstWithIntegers.delete(5); //dzēšam pašu root, kam ir abi bērni
			bstWithIntegers.print();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
