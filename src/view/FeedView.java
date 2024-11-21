package view;

import java.util.Scanner;

import entities.Photo;
import entities.User;
import service.PhotoService;

public class FeedView extends ViewMethods {
	private static Scanner scan = new Scanner(System.in);
	
	public static int main(User user) {
		int option = 2;
		do {
			Photo[] photos = PhotoService.getAll();
			System.out.println("        FEED        ");
			showPhotos(photos);
			System.out.println("[0] - Acessar uma foto especifica");
			System.out.println("[1] - Meu perfil");
			System.out.println("[2] - Buscar um perfil");
			System.out.println("[3] - Sair do programa");
			option = getIntInput(0, 2);
			switch(option) {
				case 0:
					System.out.println("Digite o codigo da foto que deseja acessar ou -1 para cancelar: ");
					int cod_photo = scan.nextInt();
					if(cod_photo != -1) {
						if(IsValidPhotoCode(photos, cod_photo)) {
							option = PhotoView.main(photos[cod_photo]);
						} else {
							System.out.println("O codigo informado é invalido");
						}
					}
					break;
				case 1:
					option = ProfileView.main(user, null);
					switch(option) {
						case 3:
							return -1;
						case 5:
							return 2;
					}
					break;
				case 2:
					option = SearchView.main();
					break;
			}
		} while(option != 3);
		return 2;
	}
	
	
	
	private static void showPhotos(Photo[] photos) {
		for(Photo p : photos) {
			String photoSTR = String.format("--------------------\n" 
										  + "       FOTO %d      \n"
					                      + " Titulo: %s\n"
										  + " Descrição: %s\n"
					                      + " Postado dia: %s\n"
										  + " Numero de likes: %d\n"
					                      + " Usuario: %s\n"
										  + " Email: %s\n"
					                      + "--------------------\n" , p.getId(), 
					                      								p.getName(),
														  			    p.getDescription(),
																	    p.getUpload_date(),
																		p.getNumber_of_likes(),
																		p.getUser().getName(),
																		p.getUser().getEmail());
			System.out.println(photoSTR);
		}
	}
}
