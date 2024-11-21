package view;

import java.util.Scanner;

import entities.Photo;
import entities.User;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.UpdateUserDTO;
import service.PhotoService;
import service.UserService;

public class ProfileView extends ViewMethods {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static int main(User user, User user_searched) {
		System.out.println("              Profile             ");
		if(user_searched == null) return MyProfile(user);
		
		return OtherProfile(user_searched);
	}
	
	private static int MyProfile(User user) {
		int option = -1;
		do {
			System.out.println("[0] - Acessar suas fotos");
			System.out.println("[1] - Acessar seus albuns");
			System.out.println("[2] - Editar sua biografia");
			System.out.println("[3] - Sair da conta");
			System.out.println("[4] - Voltar");
			System.out.println("[5] - Sair do programa");
			option = getIntInput(0, 7);
			switch(option) {
				case 0:
					photos(user.getId());
					break;
				case 1:
					break;
				case 2:
					editBiografy(user);
					break;
			}
		} while(option >= 0 && option <= 2);
		
		return option;
	}
	
	private static void editBiografy(User user) {
		System.out.print("Escreva uma nova biografia ou digite 'e' para cancelar: ");
		String biografy = scan.next().trim();
		
		if(biografy.equals("e")) return;
		
		boolean updated = UserService.update(new UpdateUserDTO("", "", "", biografy), user.getId());
		
		if(updated) System.out.println("Biografia atualzada");
	}
	
	private static void photos(int cod_user) {
		int option = -1;
		int cod_photo = -1;
		
		do {
			Photo[] photos = PhotoService.getAllByUser(cod_user);
			if(!showAllPhotos(photos)) {
				return;
			}
			System.out.println("[0] - Postar uma nova foto");
			System.out.println("[1] - Acessar uma foto especifica");
			System.out.println("[2] - Adicionar uma foto a um album");
			System.out.println("[3] - Deletar uma foto");
			System.out.println("[4] - Voltar");
			option = getIntInput(0, 3);
			switch(option) {
				case 0:
					postNewPhoto(cod_user);
					break;
				case 1:
					System.out.println("Digite o codigo da foto que deseja acessar ou -1 para cancelar: ");
					cod_photo = scan.nextInt();
					if(cod_photo != -1) {
						if(IsValidPhotoCode(photos, cod_photo)) {
							option = PhotoView.main(photos[cod_photo]);
						} else {
							System.out.println("O codigo informado é invalido");
						}
					}
					break;
				case 2:
					//Add em um album
					break;
				case 3:
					System.out.println("Digite o codigo da foto que deseja acessar ou -1 para cancelar: ");
					cod_photo = scan.nextInt();
					if(cod_photo != -1) {
						deletePhoto(photos, cod_photo);
					}
					return;
			}
		} while(option != 4);
	}
	
	private static void deletePhoto(Photo[] photos, int cod_photo) {
		if(IsValidPhotoCode(photos, cod_photo)) {
			if(PhotoService.delete(cod_photo)) System.out.println("Foto deletada");
		} else {
			System.out.println("O codigo informado é invalido");
		}
	}
	
	private static void postNewPhoto(int cod_user) {
		System.out.print("Digite um titulo para a foto ou 'e' para cancelar: ");
		String title = scan.next().trim();
		
		if(title.equals("e")) return;
		
		System.out.print("Digite uma descrição para a foto ou 'e' para cancelar: ");
		String description = scan.next().trim();
		
		if(description.equals("e")) return;
		
		boolean created = PhotoService.create(new CreatePhotoDTO(title, description), cod_user);
		
		if(created) {
			System.out.println("------------------------");
			System.out.println("      Foto postada      ");
			System.out.println("------------------------");
		}
	}
	
	private static boolean showAllPhotos(Photo[] photos) {
		if(photos == null) {
			System.out.println("------------------------");
			System.out.println("  Nenhuma foto postada  ");
			System.out.println("------------------------");
			return false;
		}
		
		for(Photo photo : photos) {
			for(Photo p : photos) {
				String photoSTR = String.format("--------------------\n" 
						 					  + "       FOTO %d      \n"
						                      + " Titulo: %s\n"
											  + " Descrição: %s\n"
						                      + " Postado dia: %s\n"
											  + " Numero de likes: %d\n"
						                      + "--------------------\n" , p.getId(),
						                      								p.getName(),
															  			    p.getDescription(),
																		    p.getUpload_date(),
																			p.getNumber_of_likes());
				System.out.println(photoSTR);
			}
		}
		
		return true;
	}
	
	private static int OtherProfile(User user_searUser) {
		int option = -1;
		do {
			System.out.println("[0] - Acessar todas as fotos de " + user_searUser.getName());
			System.out.println("[1] - Acessar todos os albuns de " + user_searUser.getName());
			System.out.println("[2] - Editar sua biografia");
			System.out.println("[3] - Sair da conta");
			System.out.println("[4] - Voltar");
			System.out.println("[5] - Sair do programa");
			option = getIntInput(0, 5);
		} while(!(option == 4 || option == 5));
		
		return option;
	}
}
