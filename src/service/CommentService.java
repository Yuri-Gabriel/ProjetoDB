package service;

import entities.Comment;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.comment.UpdateCommentDTO;
import repository.comment.CommentRepository;

public class CommentService {
	
	private static CommentRepository repository = new CommentRepository();

	public static Comment[] getAllCommentByPhoto(int cod_photo) {
		try {
			if(cod_photo < 0) throw new Exception("ERRO (CommentService.getAllCommentByPhoto):\nO código identificador (cod_photo) informado é inválido");
			
			return repository.getAllCommentByPhoto(cod_photo);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return null;
	}

	public static boolean create(CreateCommentDTO comment) {
		try {
			if(comment == null) throw new Exception("ERRO (CommentService.create):\nA entitdade CreateCommentDTO não foi enviada");
			
			if(comment.getText() == "") throw new Exception("ERRO (CommentService.create):\nO atributo text da entidade CreateCommentDTO não foi informado");
			if(comment.getCod_user() < 0) throw new Exception("ERRO (CommentService.create):\nO código identificador (cod_user) informado é inválido");
			if(comment.getCod_photo() < 0) throw new Exception("ERRO (CommentService.create):\nO código identificador (cod_photo) informado é inválido");
			
			repository.create(comment);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	public boolean update(UpdateCommentDTO comment, int cod_comment) {
		try {
			if(comment == null) throw new Exception("ERRO (CommentService.update):\nA entitdade UpdateCommentDTO não foi enviada");
			
			if(comment.getText() == "") throw new Exception("ERRO (CommentService.update):\nO atributo text da entidade UpdateCommentDTO não foi informado");
			
			repository.update(comment, cod_comment);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	public static boolean delete(int cod_comment) {
		try {
			if(cod_comment < 0) throw new Exception("ERRO (CommentService.delete):\nO código identificador (cod_comment) informado é inválido");
			
			return repository.delete(cod_comment);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
