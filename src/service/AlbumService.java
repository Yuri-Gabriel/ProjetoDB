package service;

import entities.Album;
import repository.album.AlbumRepository;

public class AlbumService {
	private static AlbumRepository repository = new AlbumRepository();
	
	public static Album[] getAllAlbumByUser(int cod_user) {
		return repository.getAllAlbumByUser(cod_user);
	}
}
