package service;

import entities.Album;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.album.UpdateAlbumDTO;
import repository.album.AlbumRepository;

public class AlbumService {
	private static AlbumRepository repository = new AlbumRepository();
	
	public static Album[] getAllAlbumByUser(int cod_user) {
		Album[] albuns = null;
		try {
			if(cod_user < 0) throw new Exception("ERRO (AlbumService.getAllAlbumByUser):\nO código identificador (cod_user) informado é inválido");
			
			albuns = repository.getAllAlbumByUser(cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return albuns;
	}
	public static boolean create(CreateAlbumDTO album, int cod_user) {
		try {
			if(album == null) throw new Exception("ERRO (AlbumService.create):\nA entitdade CreateAlbumDTO não foi enviada");
			
			if(album.getName() == "") throw new Exception("ERRO (AlbumService.create):\nO atributo name da entidade CreateAlbumDTO não foi informado");
			
			if(cod_user < 0) throw new Exception("ERRO (AlbumService.create):\nO código identificador (cod_user) informado é inválido");
			
			repository.create(album, cod_user);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean update(UpdateAlbumDTO album, int cod_album) {
		try {
			if(album == null) throw new Exception("ERRO (AlbumService.update):\nA entitdade UpdateAlbumDTO não foi enviada");
			
			if(cod_album < 0) throw new Exception("ERRO (AlbumService.update):\nO código identificador (cod_album) informado é inválido");
			
			repository.update(album, cod_album);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean addPhoto(int cod_album, int cod_photo) {
		try {
			if(cod_album < 0) throw new Exception("ERRO (AlbumService.addPhoto):\nO código identificador (cod_album) informado é inválido");
			if(cod_photo < 0) throw new Exception("ERRO (AlbumService.addPhoto):\nO código identificador (cod_photo) informado é inválido");
			
			repository.addPhoto(cod_album, cod_photo);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	public static boolean delete(int cod_album) {
		try {
			if(cod_album < 0) throw new Exception("ERRO (AlbumService.delete):\nO código identificador (cod_album) informado é inválido");
			
			repository.delete(cod_album);
			return true;
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}