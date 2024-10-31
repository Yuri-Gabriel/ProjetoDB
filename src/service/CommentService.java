package service;

import entities.Comment;
import repository.comment.CommentRepository;

public class CommentService {
	
	private static CommentRepository reporitory = new CommentRepository();
	
	public Comment[] getAllCommentByUser(int cod_user) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Comment[] getAllCommentByPhoto(int cod_photo) {
		try {
			if(cod_photo < 0) throw new Exception("Codigo identificador inválido");
			return reporitory.getAllCommentByPhoto(cod_photo);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return null;
	}

	public static boolean create(Comment comment, int cod_photo, int cod_user) {
		try {
			if(comment == null) throw new Exception("Ausencia de dados");
			reporitory.create(comment, cod_photo, cod_user);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	public static boolean delete(int cod_comment) {
		try {
			if(cod_comment < 0) throw new Exception("Dados invalidos");
			return reporitory.delete(cod_comment);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
