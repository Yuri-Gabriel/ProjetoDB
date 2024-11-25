package view;

import java.util.Scanner;

import entities.Photo;
import entities.User;
import service.PhotoService;
import service.UserService;

public class FeedView extends ViewMethods {
	private static Scanner scan = new Scanner(System.in);
	private static int numberMaxOfPhotosToShow = 10; 
	
	public static int main(User user) throws Exception {
		
		int option = 2;
		do {
			Photo[] photos = PhotoService.getAll();
			System.out.println("<><><><><><><><><><>");
			System.out.println("        FEED        ");
			System.out.println("<><><><><><><><><><>");
			showPhotos(photos);
			System.out.println("[0] - Acessar uma foto especifica");
			System.out.println("[1] - Meu perfil");
			System.out.println("[2] - Buscar um perfil");
			System.out.println("[3] - Exibir mais fotos");
			System.out.println("[4] - Sair do programa");
			option = getIntInput(0, 4);
			switch(option) {
				case 0:
					System.out.println("Digite o codigo da foto que deseja acessar ou -1 para cancelar: ");
					int cod_photo = getNextInt();
					if(cod_photo != -1) {
						if(IsValidPhotoCode(photos, cod_photo)) {
							for(Photo p : photos) {
								if(p.getId() == cod_photo) option = PhotoView.main(p, user);
							}
							if(option == 5) option = 3;
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
						case 4:
							option = -1;
							break;
					}
					break;
				case 2:
					System.out.print("Digite o username do usuario ou 'e' para cancelar: ");
					String name = scan.nextLine().trim();
					
					if(!name.equalsIgnoreCase("e")) {
						User[] users = UserService.getAll();
						for(User u : users) {
							if(u.getName().equalsIgnoreCase(name)) option = ProfileView.main(user, u);
							switch(option) {
							case 3:
								return -1;
							case 5:
								return 2;
						}
						}
					}
					break;
				case 3:
					if(numberMaxOfPhotosToShow < photos.length) {
						numberMaxOfPhotosToShow += 5;
					}
					
					break;
			}
		} while(option != 4);
		return 2;
	}
	
	
	
	private static void showPhotos(Photo[] photos) {
		if(photos == null || photos.length == 0) {
			System.out.println("------------------------");
			System.out.println("  Nenhuma foto postada  ");
			System.out.println("------------------------");
			return;
		}
		for(int i = 0; i < numberMaxOfPhotosToShow; i++) {
			String photoSTR = String.format("--------------------\n" 
										  + "       FOTO %d      \n"
					                      + " Titulo: %s\n"
										  + " Descrição: %s\n"
					                      + " Postado dia: %s\n"
										  + " Numero de likes: %d\n"
					                      + " Usuario: %s\n"
										  + " Email: %s\n"
					                      + "--------------------\n" , photos[i].getId(), 
					                      								photos[i].getName(),
					                      								photos[i].getDescription(),
					                      								photos[i].getUpload_date(),
					                      								photos[i].getNumber_of_likes(),
					                      								photos[i].getUser().getName(),
					                      								photos[i].getUser().getEmail());
			System.out.println(photoSTR);
		}
		if(numberMaxOfPhotosToShow == photos.length) {
			System.out.println("Todas as fotos ja foram exibidas");
		}
	}
}
