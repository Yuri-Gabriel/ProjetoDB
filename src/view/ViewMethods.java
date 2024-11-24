package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Album;
import entities.Comment;
import entities.Photo;

public class ViewMethods {
	private static Scanner scan = new Scanner(System.in);
	
	public static int getIntInput(int minBound, int maxBound) {
		int value = -1;
		do {
			try {
				System.out.print("Sua opção: ");
				value = scan.nextInt();
				if(value < minBound || value > maxBound) {
					System.out.println("Opção invalida, tente novamente");
				}
			} catch (InputMismatchException err) {
				System.out.println("Opção invalida, tente novamente");
				scan.nextLine();
			}
			
		} while(value < minBound || value > maxBound);
		return value;
	}
	
	public static boolean IsValidPhotoCode(Photo[] photos, int cod_photo) {
		for(int i = 0; i < photos.length; i++) {
			if(cod_photo == photos[i].getId()) return true;
		}
		return false;
	}
	
	public static boolean IsValidCommentCode(Comment[] comments, int cod_comment) {
		for(int i = 0; i < comments.length; i++) {
			if(cod_comment == comments[i].getId()) return true;
		}
		return false;
	}
	
	public static boolean IsValidAlbumCode(Album[] albuns, int cod_album) {
		for(int i = 0; i < albuns.length; i++) {
			if(cod_album == albuns[i].getId()) return true;
		}
		return false;
	}
}
