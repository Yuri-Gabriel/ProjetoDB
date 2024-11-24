package view;

import java.util.Scanner;

import entities.Album;
import entities.Photo;
import entities.User;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.album.UpdateAlbumDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.UpdateUserDTO;
import service.AlbumService;
import service.PhotoService;
import service.UserService;

public class ProfileView extends ViewMethods {
	
	private static Scanner scan = new Scanner(System.in);
	private static User user_login = null;
	
	public static int main(User user, User user_searched) {
		user_login = user;
		
		if(user_searched == null) return MyProfile();
		
		return OtherProfile(user_searched);
	}
	
	private static int MyProfile() {
		int option = -1;
		do {
			System.out.println("<><><><><><><><><><><><>");
			System.out.println("         PROFILE        ");
			System.out.println("<><><><><><><><><><><><>");
			showUserData(user_login);
			System.out.println("[0] - Acessar suas fotos");
			System.out.println("[1] - Acessar seus albuns");
			System.out.println("[2] - Editar sua biografia");
			System.out.println("[3] - Sair da conta");
			System.out.println("[4] - Voltar");
			System.out.println("[5] - Sair do programa");
			option = getIntInput(0, 5);
			switch(option) {
				case 0:
					option = photos();
					break;
				case 1:
					option = albuns();
					if(option == 4) option = 5;
					break;
				case 2:
					editBiografy();
					break;
			}
		} while(option >= 0 && option <= 2);
		
		return option;
	}
	
	private static void showUserData(User user) {
		String userSTR = String.format("=====================\n" 
									 + " Nome: %s\n"
									 + " Email: %s\n"
									 + " Biografia: %s\n" 
									 + "=====================\n" , user.getName(),
									 								user.getEmail(),
									 								user.getBiography());
		System.out.println(userSTR);
	}
	
	private static void editBiografy() {
		System.out.print("Escreva uma nova biografia ou digite 'e' para cancelar: ");
		String biografy = scan.next().trim();
		
		if(biografy.equals("e")) return;
		
		boolean updated = UserService.update(new UpdateUserDTO("", "", "", biografy), user_login.getId());
		
		if(updated) System.out.println("Biografia atualzada");
	}
	
	private static int albuns() {
		int option = -1;
		int cod_album = -1;
		
		do {
			Album[] albuns = AlbumService.getAllAlbumByUser(user_login.getId());
			if(!showAllAlbuns(albuns)) {
				System.out.println("Você não possui albuns, deseja criar um? (s/n) ");
				String answer = "";
				
				do {
					System.out.print("Sua escolha: ");
					answer = scan.next().trim().toLowerCase();
					
					if(!(answer.equals("s") || answer.equals("n"))) {
						System.out.println("Opção inválida");
					}
				} while (!(answer.equals("s") || answer.equals("n")));
				
				if(answer.equals("n")) return 3;
				
				createNewAlbum();
			}
			System.out.println("[0] - Acessar um album");
			System.out.println("[1] - Criar um novo album");
			System.out.println("[2] - Deletar um album");
			System.out.println("[3] - Voltar");
			System.out.println("[4] - Sair do programa");
			option = getIntInput(0, 3);
			switch(option) {
				case 0:
					System.out.println("Digite o codigo do album que deseja acessar ou -1 para cancelar: ");
					cod_album = scan.nextInt();
					if(cod_album != -1) {
						if(IsValidAlbumCode(albuns, cod_album)) {
							for(Album a : albuns) {
								if(a.getId() == cod_album) showOneAlbum(a);
							}
						} else {
							System.out.println("O codigo informado é invalido");
						}
					}
					break;
				case 1:
					createNewAlbum();
					break;
				case 2:
					System.out.print("Digite o codigo do album que deseja deletar ou -1 para cancelar: ");
					cod_album = scan.nextInt();
					if(cod_album != -1) {
						deleteAlbum(albuns, cod_album);
					}
					break;
				case 3:
					return -1;
			}
		} while(!(option == 3 || option == 4));
		return option;
	}
	
	private static void deleteAlbum(Album[] albuns, int cod_album) {
		if(IsValidAlbumCode(albuns, cod_album)) {
			if(AlbumService.delete(cod_album)) System.out.println("Album deletado");
		} else {
			System.out.println("O codigo informado é invalido");
		}
	}
	
	private static boolean createNewAlbum() {
		System.out.print("Digite um nome para o album (obrigatorio) ou 'e' para cancelar: ");
		String name = scan.next().trim();
		
		if(name.equals("")) {
			System.out.println("ERRO: Não foi digitado um nome para o album");
			return false;
		}
		if(name.equals("e")) return false;
		
		System.out.print("Digite uma descrição para o album (opcional) ou 'e' para cancelar: ");
		String description = scan.next().trim();
		
		if(name.equals("e")) return false;
		
		boolean created = AlbumService.create(new CreateAlbumDTO(name, description), user_login.getId());
		if(created) System.out.println("Album criado");
		
		return created;
	}
	
	private static int showOneAlbum(Album album) {
		int option = -1;
		int cod_photo = -1;
		
		if(album.getPhotos() == null) {
			System.out.println("------------------------");
			System.out.println("   O Album esta vazio   ");
			System.out.println("------------------------");
			return 1;
		}
		
		System.out.println(String.format("========================\n"
									   + "        %s\n"
									   + "========================\n", album.getName()));
		
		for(Photo p : album.getPhotos()) {
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
		
		System.out.println( "========================\n");
		
		
		do {
			System.out.println("[0] - Remover uma foto");
			System.out.println("[1] - Editar o nome ou descrição do album");
			System.out.println("[2] - Voltar");
			option = getIntInput(0, 2);
			switch(option) {
				case 0:
					System.out.print("Digite o codigo da foto que deseja deletar ou -1 para cancelar: ");
					cod_photo = scan.nextInt();
					if(cod_photo != -1) {
						if(IsValidPhotoCode(album.getPhotos(), cod_photo)) {
							removePhotoFromAlbum(album.getId(), cod_photo);
							return 0;
						} else {
							System.out.println("O codigo informado é invalido");
						}
					}
					break;
				case 1:
					
					System.out.print("Digite um novo nome para o album ou 'p' para pular: ");
					String newName = scan.next().trim();
					
					System.out.print("Digite uma nova descrição para o album ou aperte 'p' para pular: ");
					String newDescription = scan.next().trim();
					
					if(!(newName.equals("p") && newDescription.equals("p"))) {
						boolean updated = AlbumService.update(new UpdateAlbumDTO(newName, newDescription), album.getId());
						if(updated) System.out.println("Informações atualizadas");
						album.setName(newName);
						album.setDescription(newDescription);
						return 0;
					}
					break;
			}
		} while(option != 2);
		return 1;
	}
	
	private static void removePhotoFromAlbum(int cod_album, int cod_photo) {
		boolean removed = AlbumService.removePhoto(cod_album, cod_photo);
		if(removed) System.out.println("Foto removida");
	}
	
	private static boolean showAllAlbuns(Album[] albuns) {
		if(albuns == null) {
			System.out.println("------------------------");
			System.out.println("       Sem albuns       ");
			System.out.println("------------------------");
			return false;
		}
		for(Album a : albuns) {
			String albumSTR = String.format("--------------------\n" 
					 					  + "      ALBUM %d      \n"
					                      + " Nome: %s\n"
										  + " Descrição: %s\n"
					                      + " Data de criação: %s\n"
					                      + "--------------------\n" , a.getId(),
					                      								a.getName(),
					                      								a.getDescription(),
					                      								a.getCreation_date());
			System.out.println(albumSTR);
		}
		
		return true;
	}
	
	
	private static void addPhotoInAlbum(int cod_user) {
		int cod_photo = -1;
		int cod_album = -1;
		
		Album[] albuns = AlbumService.getAllAlbumByUser(cod_user);
		if(!showAllAlbuns(albuns)) {
			System.out.println("Você não possui albuns, deseja criar um? (s/n) ");
			String answer = "";
			
			do {
				System.out.print("Sua escolha: ");
				answer = scan.next().trim().toLowerCase();
				
				if(!(answer.equals("s") || answer.equals("n"))) {
					System.out.println("Opção inválida");
				}
			} while (!(answer.equals("s") || answer.equals("n")));
			
			if(answer.equals("n")) return;
			
			if(createNewAlbum()) return;
		}
		
		Photo[] photos = PhotoService.getAllByUser(cod_user);
		
		System.out.print("Digite o codigo da foto escolhida ou -1 para cancelar: ");
		cod_photo = scan.nextInt();
		
		if(cod_photo == -1) return;
		
		if(IsValidPhotoCode(photos, cod_photo)) {
			System.out.print("Digite o codigo do album onde quer por a foto ou -1 para cancelar: ");
			cod_album = scan.nextInt();
			
			if(cod_album == -1) return;
			
			if(IsValidAlbumCode(albuns, cod_album)) {
				boolean alreadyAdded = AlbumService.toCheckIfPhotoAlreadyIsInAlbum(cod_album, cod_photo);
				if(!alreadyAdded) {
					boolean added = AlbumService.addPhoto(cod_album, cod_photo);
					System.out.println("Foto adicionada");
				} else {
					System.out.println("Essa foto ja foi adicionada ao album");
				}
				
			} else {
				System.out.println("O codigo de album informado é inválido");
			}
		} else {
			System.out.println("O codigo de foto informado é inválido");
		}
	}
	
	
	private static int photos() {
		int option = -1;
		int cod_photo = -1;
		
		do {
			Photo[] photos = PhotoService.getAllByUser(user_login.getId());
			if(!showAllPhotos(photos)) {
				return 4;
			}
			System.out.println("[0] - Postar uma nova foto");
			System.out.println("[1] - Acessar uma foto especifica");
			System.out.println("[2] - Adicionar uma foto a um album");
			System.out.println("[3] - Deletar uma foto");
			System.out.println("[4] - Voltar");
			System.out.println("[5] - Sair do programa");
			option = getIntInput(0, 5);
			switch(option) {
				case 0:
					postNewPhoto();
					break;
				case 1:
					System.out.println("Digite o codigo da foto que deseja acessar ou -1 para cancelar: ");
					cod_photo = scan.nextInt();
					if(cod_photo != -1) {
						if(IsValidPhotoCode(photos, cod_photo)) {
							for(Photo p : photos) {
								if(p.getId() == cod_photo) option = PhotoView.main(p, user_login);
								if(option == 3) option = 5;
							}
						} else {
							System.out.println("O codigo informado é invalido");
						}
					}
					break;
				case 2:
					addPhotoInAlbum(user_login.getId());
					break;
				case 3:
					System.out.print("Digite o codigo da foto que deseja deletar ou -1 para cancelar: ");
					cod_photo = scan.nextInt();
					if(cod_photo != -1) {
						deletePhoto(photos, cod_photo);
					}
					return 4;
			}
		} while(!(option == 4 || option == 5));
		return option;
	}
	
	private static void deletePhoto(Photo[] photos, int cod_photo) {
		if(IsValidPhotoCode(photos, cod_photo)) {
			if(PhotoService.delete(cod_photo)) System.out.println("Foto deletada");
		} else {
			System.out.println("O codigo informado é invalido");
		}
	}
	
	private static void postNewPhoto() {
		System.out.print("Digite um titulo para a foto ou 'e' para cancelar: ");
		String title = scan.next().trim();
		
		if(title.equals("e")) return;
		
		System.out.print("Digite uma descrição para a foto ou 'e' para cancelar: ");
		String description = scan.next().trim();
		
		if(description.equals("e")) return;
		
		boolean created = PhotoService.create(new CreatePhotoDTO(title, description), user_login.getId());
		
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
		
		return true;
	}
	
	private static int OtherProfile(User user_searched) {
		int option = -1;
		do {
			System.out.println("<><><><><><><><><><><><>");
			System.out.println("         PROFILE        ");
			System.out.println("<><><><><><><><><><><><>");
			showUserData(user_searched);
			System.out.println("[0] - Acessar todas as fotos de " + user_searched.getName());
			System.out.println("[1] - Acessar todos os albuns de " + user_searched.getName());
			System.out.println("[2] - Voltar");
			System.out.println("[3] - Sair do programa");
			option = getIntInput(0, 3);
			
			switch(option) {
				case 0:
					viewPhotosFromOtherUser(user_searched.getId());
					break;
				case 1:
					viewAlbunsFromOtherUser(user_searched.getId());
					break;
			}
		} while(!(option == 2 || option == 3));
		
		return option;
	}
	
	private static void viewAlbunsFromOtherUser(int cod_user) {
		Album[] albuns = AlbumService.getAllAlbumByUser(cod_user);
		if(albuns == null || albuns.length == 0) {
			System.out.println("--------------------------------");
			System.out.println(" Esse usuario não possui albuns ");
			System.out.println("--------------------------------");
			return;
		}
		
		for(Album a : albuns) {
			String albumSTR = String.format("--------------------\n" 
					 					  + "      ALBUM %d      \n"
					                      + " Nome: %s\n"
										  + " Descrição: %s\n"
					                      + " Data de criação: %s\n"
					                      + "--------------------\n" , a.getId(),
					                      								a.getName(),
					                      								a.getDescription(),
					                      								a.getCreation_date());
			System.out.println(albumSTR);
		}
		
		int option = -1;
		int cod_album = -1;
		
		System.out.println("[0] - Acessar um album");
		System.out.println("[1] - Voltar");
		option = getIntInput(0, 1);
		switch(option) {
			case 0:
				System.out.println("Digite o codigo do album que deseja acessar ou -1 para cancelar: ");
				cod_album = scan.nextInt();
				if(cod_album != -1) {
					if(IsValidAlbumCode(albuns, cod_album)) {
						for(Album a : albuns) {
							if(a.getId() == cod_album) {
								if(a.getPhotos() == null) {
									System.out.println("--------------------------------");
									System.out.println("   Esse album não possui fotos  ");
									System.out.println("--------------------------------");
									return;
								}
								for(Photo p : a.getPhotos()) {
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
								System.out.println("[0] - Acessar uma foto especifica");
								System.out.println("[1] - Voltar");
								option = getIntInput(0, 1);
								
								if(option == 0) {
									System.out.print("Digite o codigo da foto ou -1 para cancelar: ");
									int cod_photo = scan.nextInt();
									
									if(cod_photo == -1) return;
									
									if(IsValidPhotoCode(a.getPhotos(), cod_photo)) {
										for(Photo p : a.getPhotos()) {
											if(p.getId() == cod_photo) PhotoView.main(p, user_login);
										}
									}
									
								}
							}
						}
					} else {
						System.out.println("O codigo informado é invalido");
					}
				}
				break;
			case 1:
				return;
		}
	}
	
	
	
	private static void viewPhotosFromOtherUser(int cod_user) {
		Photo[] photos = PhotoService.getAllByUser(cod_user);
		if(photos == null) {
			System.out.println("------------------------");
			System.out.println("  Nenhuma foto postada  ");
			System.out.println("------------------------");
			return;
		}
		
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
		System.out.println("[0] - Acessar uma foto especifica");
		System.out.println("[1] - Voltar");
		int option = getIntInput(0, 1);
		
		if(option == 0) {
			System.out.print("Digite o codigo da foto ou -1 para cancelar: ");
			int cod_photo = scan.nextInt();
			
			if(cod_photo == -1) return;
			
			if(IsValidPhotoCode(photos, cod_photo)) {
				for(Photo p : photos) {
					if(p.getId() == cod_photo) PhotoView.main(p, user_login);
				}
			}
			
		}
	}
}
