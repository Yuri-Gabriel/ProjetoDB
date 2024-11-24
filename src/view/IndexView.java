package view;

import java.util.Scanner;

import entities.User;
import entities.dto.user.CreateUserDTO;
import service.UserService;

public class IndexView extends ViewMethods {
	private static Scanner scan = new Scanner(System.in);
	
	public static void main() {
		int option = -1;
		do {
			System.out.println("       Login");
			System.out.println("[0] - Realizar login");
			System.out.println("[1] - Criar uma conta");
			System.out.println("[2] - Sair do programa");
			
			option = getIntInput(0, 2);
			
			switch (option) {
				case 0:
					User user = doLogin();
					if(user != null) option = FeedView.main(user);
					break;
				case 1:
					doSingUp();
					break;
			}
		} while(option != 2);
		System.out.println("\nFim do programa");
		
	}
	
	private static void doSingUp() {
		System.out.print("Digite seu nome de usuario ou 'e' para sair: ");
		String nome = scan.next().trim();
		
		if(nome.equals("e")) return;
		
		System.out.print("Digite um email ou 'e' para sair: ");
		String email = scan.next().trim();
		
		if(email.equals("e")) return;
		if(!email.contains("@")) {
			System.out.println("Endereço de email invalido");
			return;
		}
		
		System.out.print("Digite uma senha ou 'e' para sair: ");
		String senha = scan.next().trim();
		
		if(senha.equals("e")) return;
		
		boolean created = UserService.create(new CreateUserDTO(nome, email, senha, ""));
		if(created) System.out.println("Usuario criado");
	}
	
	private static User doLogin() {
		User user = null;
		
		System.out.print("Digite seu email ou 'e' para sair: ");
		String email = scan.next().trim();
		
		if(email.equals("e")) return null;
		
		System.out.print("Digite sua senha ou 'e' para sair: ");
		String senha = scan.next().trim();
		
		if(senha.equals("e")) return null;
		
		user = UserService.get(email, senha);
			
		return user;
	}
	
	
}
