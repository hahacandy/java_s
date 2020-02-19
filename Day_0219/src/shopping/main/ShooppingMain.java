package shopping.main;

import java.util.Scanner;

import shopping.ui.ShoppingUI;

public class ShooppingMain {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		ShoppingUI ui = new ShoppingUI();
		
		ui.start(scn);

	}

}
