package repository.album;

import entities.Album;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.album.UpdateAlbumDTO;

public interface AlbumRepositoryInterface {
	public Album[] getAllAlbumByUser(int cod_user);
	public boolean create(CreateAlbumDTO album, int cod_user);
	public boolean update(UpdateAlbumDTO album, int cod_album);
	public boolean addPhoto(int cod_album, int cod_photo);
	public boolean delete(int cod_album);
}