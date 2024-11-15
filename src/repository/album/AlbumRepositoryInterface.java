package repository.album;

import entities.Album;
<<<<<<< HEAD

public interface AlbumRepositoryInterface {
	public Album[] getAllAlbumByUser(int cod_user);
	public boolean create(Album album, int cod_user);
	public boolean addPhoto(int cod_album, int cod_photo);
	public boolean delete(int cod_album);
}
=======
import entities.dto.album.CreateAlbumDTO;
import entities.dto.album.UpdateAlbumDTO;

public interface AlbumRepositoryInterface {
	public Album[] getAllAlbumByUser(int cod_user);
	public boolean create(CreateAlbumDTO album, int cod_user);
	public boolean update(UpdateAlbumDTO album, int cod_album);
	public boolean addPhoto(int cod_album, int cod_photo);
	public boolean delete(int cod_album);
}
>>>>>>> bd1d6228ab1213ef626083c538174680c11198f9
