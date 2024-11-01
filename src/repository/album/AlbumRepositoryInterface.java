package repository.album;

import entities.Album;

public interface AlbumRepositoryInterface {
	public Album[] getAllAlbumByUser(int cod_user);
	public boolean create(Album album, int cod_user);
	public boolean addPhoto(int cod_album, int cod_photo);
	public boolean delete(int cod_album);
}
