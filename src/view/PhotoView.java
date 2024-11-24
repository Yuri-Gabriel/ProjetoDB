package view;

import java.util.Scanner;

import entities.Comment;
import entities.Photo;
import entities.User;
import entities.dto.comment.CreateCommentDTO;
import service.CommentService;
import service.PhotoService;

public class PhotoView extends ViewMethods {
	
	private static Photo photo = null;
	private static User user = null;
	private static Comment[] comments = null;
	private static boolean userAlreadyLiked = false;
	
	private static Scanner scan = new Scanner(System.in);
	
	public static int main(Photo p, User u) {
		
		photo = p;
		user = u;
		boolean alreadyLiked  = PhotoService.toCheckIfUserAlreadGaveLike(photo.getId(), user.getId());
		if(alreadyLiked) userAlreadyLiked = true;
		
		int option = -1;
		
		do {
			showPhoto(photo);
			System.out.println(String.format("[0] - %s like", userAlreadyLiked ? "Remover o" : "Dar um"));
			System.out.println("[1] - Vizualizar os comentarios");
			System.out.println("[2] - Voltar");
			System.out.println("[3] - Sair do programa");
			option = getIntInput(0, 3);
			switch(option) {
				case 0:
					likeOperations();
					break;
				case 1:
					option = commentsOperations();
					if(option == 3) return 3;
					break;
				case 3:
					return 3;
			}
		} while(option != 2);
		return option;
	
	}
	
	private static void likeOperations() {
		if(!userAlreadyLiked) {
			boolean liked = PhotoService.giveOneLike(photo.getId(), user.getId());
			if(liked) {
				photo.setNumber_of_likes(photo.getNumber_of_likes() + 1);
				userAlreadyLiked = true;
			}
		} else {
			boolean unliked = PhotoService.ungiveOneLike(photo.getId(), user.getId());
			if(unliked) {
				photo.setNumber_of_likes(photo.getNumber_of_likes() - 1);
				userAlreadyLiked = false;
			}
		}
		
	}
	
	private static int commentsOperations() {
		int option = -1;
		do {
			comments = CommentService.getAllCommentByPhoto(photo.getId());
			showComments();
			System.out.println("[0] - Comentar na foto");
			System.out.println("[1] - Remover um comentario");
			System.out.println("[2] - Voltar");
			System.out.println("[3] - Sair do programa");
			option = getIntInput(0, 3);
			
			switch(option) {
				case 0:
					makeComment();
					break;
				case 1:
					removeComment();
					break;
			}
		} while(option != 2);
		return option;
	}
	
	private static void removeComment() {
		System.out.print("Digite o codigo do seu comentario que deseja remover ou -1 para cancelar: ");
		int cod_comment = scan.nextInt();
		
		if(cod_comment == -1) return;
		
		if(IsValidCommentCode(comments, cod_comment)) {
			if(theCommentsIsFromUser()) {
				boolean removed = CommentService.delete(cod_comment);
				if(removed) System.out.println("Comentario deletado");
			} else {
				System.out.println("Esse comentario não é seu");
			}
		} else {
			System.out.println("O codigo informado é invalido");
		}
	}
	
	private static boolean theCommentsIsFromUser() {
		for(Comment c : comments) {
			if(c.getUser().getId() == user.getId()) return true;
		}
		return false;
	}
	
	private static void makeComment() {
		System.out.print("Digite o texto do comentario ou 'e' para cancelar: ");
		String text = scan.next().trim();
		
		if(text.equals("e")) return;
		
		boolean created = CommentService.create(new CreateCommentDTO(text, user.getId(), photo.getId()));
		if(created) System.out.println("Comentario realizado");
		
	}
	
	private static void showComments() {
		for(Comment c : comments) {
			String commentSTR = String.format("--------------------\n" 
										    + "    COMENTARIO %d   \n"
					                        + " Texto: %s\n"
					                        + " Postado dia: %s\n"
					                        + " Feito por: %s\n"
					                        + "--------------------\n", c.getId(),
					                        								c.getText(),
					                        								c.getDate(),
					                        								c.getUser().getName());
			System.out.println(commentSTR);
		}
	}
	
	private static void showPhoto(Photo photo) {
		String photoSTR = String.format("--------------------\n" 
									  + "       FOTO %d      \n"
									  + " Titulo: %s\n"
									  + " Descrição: %s\n"
									  + " Postado dia: %s\n"
									  + " Numero de likes: %d\n"
									  + "--------------------\n" , photo.getId(),
					                								photo.getName(),
					                								photo.getDescription(),
					                								photo.getUpload_date(),
					                								photo.getNumber_of_likes());
		System.out.println(photoSTR);
	}
}
