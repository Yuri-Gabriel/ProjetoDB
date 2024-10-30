package service;

import entities.Photo;
import repository.photo.PhotoRepository;

public class PhotoService {
	private static PhotoRepository repository = new PhotoRepository();
	
	public static Photo[] getAllByUser(int cod_user) {
		Photo[] photos = repository.getAllByUser(cod_user);
		try {
			if(photos == null) throw new Exception("Sem fotos postadas");
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return photos;
	}
	public static boolean create(Photo photo, int cod_user) {
		try {
			if (photo == null) throw new Exception("Ausencia de dados");
			return repository.create(photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean update(Photo photo) {
		return false;
	}
	public static boolean delete(int cod_photo) {
		try {
			if (cod_photo < 0) throw new Exception("Código identificador inválido");
			return repository.delete(cod_photo);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean incrementNumberOfLikes(int cod_photo) {
		return repository.incrementNumberOfLikes(cod_photo);
	}
	public static boolean decrementNumberOfLikes(int cod_photo) {
		return repository.decrementNumberOfLikes(cod_photo);
	}
}
