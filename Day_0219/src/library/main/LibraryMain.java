package library.main;

import java.util.Scanner;

import library.ui.Ui;

public class LibraryMain {

	public static void main(String[] args) {
		Ui ui = new Ui();
		Scanner scn =new Scanner(System.in);
		
		ui.start(scn);
	}

}
