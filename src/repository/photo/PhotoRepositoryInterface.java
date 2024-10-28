package repository.photo;

import entities.Photo;

public interface PhotoRepositoryInterface {
	public Photo[] getAllByUser(int cod_user);
	public Photo get(int cod_photo);
	public boolean create(Photo photo);
	public boolean update(Photo photo);
	public boolean delete(int cod_photo);
}
