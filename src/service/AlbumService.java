package service;

import entities.Album;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.album.UpdateAlbumDTO;
import repository.album.AlbumRepository;

public class AlbumService {
	private static AlbumRepository repository = new AlbumRepository();
	
	public static Album[] getAllAlbumByUser(int cod_user) {
		return repository.getAllAlbumByUser(cod_user);
	}
	public static boolean create(CreateAlbumDTO album, int cod_user) {
		try {
			if(album == null) throw new Exception("Ausencia de dados");
			repository.create(album, cod_user);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean update(UpdateAlbumDTO album, int cod_album) {
		try {
			if(album == null) throw new Exception("Ausencia de dados");
			repository.update(album, cod_album);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean addPhoto(int cod_album, int cod_photo) {
		try {
			if(cod_album < 0 || cod_photo < 0) throw new Exception("Dados inválidos");
			repository.addPhoto(cod_album, cod_photo);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean delete(int cod_album) {
		try {
			if(cod_album < 0) throw new Exception("Dados inválidos");
			repository.delete(cod_album);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}