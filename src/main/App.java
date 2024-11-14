package main;

import java.util.Scanner;

import entities.*;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.CreateUserDTO;
import service.*;

public class App {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception { 
		String repeat = "s";
		int optionToManipulate = -1;
		
		do {
			
			System.out.println("ProjetoDB");
			System.out.println("Qual tabela você quer manipular?");
			System.out.println("[0] - User");
			System.out.println("[1] - Photo");
			System.out.println("[2] - Album");
			System.out.println("[3] - Comment");
			System.out.println("[4] - Sair do programa");
			do {
				System.out.print("Sua escolha: ");
				optionToManipulate = scan.nextInt();
				if(optionToManipulate < 0 || optionToManipulate > 4) {
					System.out.println("Opção invalida");
				}
			} while (optionToManipulate < 0 || optionToManipulate > 4);
			switch (optionToManipulate) {
				case 0:
					manipulateUserTable();
					break;
				case 1:
					manipulatePhotoTable();
					break;
				case 2:
					manipulateAlbumTable();
					break;
				case 3:
					manipulateCommentTable();
					break;
			}
			System.out.println("Deseja manipular mais alguma tabela? ");
			do {
				System.out.print("Sua escolha: ");
				repeat = scan.next();
				if(!(repeat.equals("s")  || repeat.equals("n"))) {
					System.out.println("Opção invalida");
				}
			} while(!(repeat.equals("s")  || repeat.equals("n")));
			System.out.println();
		} while(repeat.equals("s"));
		System.out.println("Fim");
	}
	
	public static void manipulateUserTable() throws Exception {
		String repeat = "s";
		int option = -1;
		do {
			System.out.println();
			System.out.println("[0] - Criar um novo usuario");
			System.out.println("[1] - Pegar um usuario especifico");
			System.out.println("[2] - Atualizar algum usuario");
			System.out.println("[3] - Remover um usuario");
			System.out.println("[4] - Listar todos os usuarios");
			System.out.println("[5] - Sair");
			do {
				System.out.print("Sua escolha: ");
				option = scan.nextInt();
				if(option < 0 || option > 5) {
					System.out.println("Opção invalida");
				}
			} while (option < 0 || option > 5);
			switch (option) {
				case 0:
					
					break;
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					System.out.println();
					User[] users = UserService.getAll();
					for(User u : users) {
						System.out.println(u.toString());
					}
					System.out.println();
					break;
			}
			System.out.println("Deseja fazer mais alguma manipulação na tabela User?");
			do {
				System.out.print("Sua escolha: ");
				repeat = scan.next();
				if(!(repeat.equals("s")  || repeat.equals("n"))) {
					System.out.println("Opção invalida");
				}
			} while(!(repeat.equals("s")  || repeat.equals("n")));
		} while(repeat.equals("s"));
		
	}
	public static void manipulatePhotoTable() {
			
	}
	public static void manipulateAlbumTable() {
		
	}
	public static void manipulateCommentTable() {
		
	}
}
