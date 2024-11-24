package service;

import entities.Photo;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.photo.UpdatePhotoDTO;
import repository.like.LikeRepository;
import repository.photo.PhotoRepository;

public class PhotoService {
	private static PhotoRepository repository = new PhotoRepository();
	private static LikeRepository like_repository = new LikeRepository();
	
	public static Photo[] getAll() {
		Photo[] photos = null;
		try {
			photos = repository.getAll();
			if(photos == null) throw new Exception("ERRO (PhotoService.getAll): Fotos não foram postadas");
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return photos;
	}
	
	public static Photo[] getAllByUser(int cod_user) {
		Photo[] photos = null;
		try {
			if(cod_user < 0)  throw new Exception("ERRO (PhotoService.getAllByUser):\nO código identificador (cod_user) informado é inválido");
				
			photos = repository.getAllByUser(cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return photos;
	}
	public static boolean create(CreatePhotoDTO photo, int cod_user) {
		try {
			if (photo == null) throw new Exception("ERRO (PhotoService.create):\nA entitdade CreateLivroDTO não foi enviada");
			
			if(photo.getName() == "") throw new Exception("ERRO (PhotoService.create):\nO atributo name da entidade CreateLivroDTO não foi informado");
			if(photo.getDescription() == "") throw new Exception("ERRO (PhotoService.create):\nO atributo description da entidade CreateLivroDTO não foi informado");
			
			if(cod_user < 0) throw new Exception("ERRO (PhotoService.create):\nO código identificador (cod_user) informado é inválido");
			
			return repository.create(photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean update(UpdatePhotoDTO photo, int cod_photo) {
		try {
			if(photo == null) throw new Exception("ERRO (PhotoService.update):\nA entitdade UpdateLivroDTO não foi enviada");
			
			if(cod_photo < 0) throw new Exception("ERRO (PhotoService.update):\nO código identificador (cod_photo) informado é inválido");
			
			return repository.update(photo, cod_photo);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean delete(int cod_photo) {
		try {
			if(cod_photo < 0) throw new Exception("ERRO (PhotoService.delete):\nO código identificador (cod_photo) informado é inválido");
			
			return repository.delete(cod_photo);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean giveOneLike(int cod_photo, int cod_user) {
		try {
			if(cod_photo < 0) throw new Exception("ERRO (PhotoService.incrementNumberOfLikes):\nO código identificador (cod_photo) informado é inválido");
			
			if(cod_user < 0) throw new Exception("ERRO (PhotoService.incrementNumberOfLikes):\nO código identificador (cod_user) informado é inválido");
			
			return like_repository.giveOneLike(cod_photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean ungiveOneLike(int cod_photo, int cod_user) {
		try {
			if(cod_photo < 0) throw new Exception("ERRO (PhotoService.decrementNumberOfLikes):\nO código identificador (cod_photo) informado é inválido");
			
			if(cod_user < 0) throw new Exception("ERRO (PhotoService.decrementNumberOfLikes):\nO código identificador (cod_user) informado é inválido");
			
			return like_repository.ungiveOneLike(cod_photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean toCheckIfUserAlreadGaveLike(int cod_photo, int cod_user) {
		try {
			if(cod_photo < 0) throw new Exception("ERRO (PhotoService.decrementNumberOfLikes):\nO código identificador (cod_photo) informado é inválido");
			
			if(cod_user < 0) throw new Exception("ERRO (PhotoService.decrementNumberOfLikes):\nO código identificador (cod_user) informado é inválido");
			
			return like_repository.toCheckIfUserAlreadGaveLike(cod_photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
