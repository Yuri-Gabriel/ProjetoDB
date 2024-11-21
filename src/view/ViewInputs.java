package view;

import java.util.Scanner;

public class ViewInputs {
	private static Scanner scan = new Scanner(System.in);
	
	public static int getIntInput(int minBound, int maxBound) {
		int value = -1;
		do {
			System.out.print("Sua opção: ");
			value = scan.nextInt();
			if(value < minBound || value > maxBound) {
				System.out.println("Opção invalida, tente novamente");
			}
		} while(value < minBound || value > maxBound);
		return value;
	}
}
