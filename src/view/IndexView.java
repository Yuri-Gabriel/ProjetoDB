package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.User;
import entities.dto.user.CreateUserDTO;
import service.UserService;

public class IndexView extends ViewMethods {
	private static Scanner scan = new Scanner(System.in);
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static void main() throws Exception {
		int option = -1;
		do {
			System.out.println("<><><><><><><><><><><><>");
			System.out.println("          Login         ");
			System.out.println("<><><><><><><><><><><><>");
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
		String nome = scan.nextLine().trim();
		
		if(nome.equalsIgnoreCase("e")) return;
		
		System.out.print("Digite um email ou 'e' para sair: ");
		String email = scan.nextLine().trim();
		
		if(email.equalsIgnoreCase("e")) return;
		if(!validateEmail(email)) {
			System.out.println("Endereço de email invalido");
			return;
		}
		
		System.out.print("Digite uma senha ou 'e' para sair: ");
		String senha = scan.nextLine().trim();
		
		if(senha.equalsIgnoreCase("e")) return;
		
		boolean created = UserService.create(new CreateUserDTO(nome, email, senha, ""));
		if(created) System.out.println("Usuario criado");
	}
	
	private static User doLogin() {
		User user = null;
		
		System.out.print("Digite seu email ou 'e' para sair: ");
		String email = scan.nextLine().trim();
		
		if(email.equalsIgnoreCase("e")) return null;
		if(!validateEmail(email)) {
			System.out.println("Endereço de email invalido");
			return null;
		}
		
		System.out.print("Digite sua senha ou 'e' para sair: ");
		String senha = scan.nextLine().trim();
		
		if(senha.equalsIgnoreCase("e")) return null;
		
		user = UserService.get(email, senha);
			
		return user;
	}
	
	public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
	}
	
	
}
