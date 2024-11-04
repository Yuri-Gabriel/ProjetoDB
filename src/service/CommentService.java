package service;

import entities.Comment;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.comment.UpdateCommentDTO;
import repository.comment.CommentRepository;

public class CommentService {
	
	private static CommentRepository repository = new CommentRepository();

	public static Comment[] getAllCommentByPhoto(int cod_photo) {
		try {
			if(cod_photo < 0) throw new Exception("Codigo identificador inválido");
			return repository.getAllCommentByPhoto(cod_photo);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return null;
	}

	public static boolean create(CreateCommentDTO comment) {
		try {
			if(comment == null) throw new Exception("Ausencia de dados");
			repository.create(comment);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	public boolean update(UpdateCommentDTO comment, int cod_comment) {
		try {
			if(comment == null) throw new Exception("Ausencia de dados");
			repository.update(comment, cod_comment);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	public static boolean delete(int cod_comment) {
		try {
			if(cod_comment < 0) throw new Exception("Dados invalidos");
			return repository.delete(cod_comment);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
