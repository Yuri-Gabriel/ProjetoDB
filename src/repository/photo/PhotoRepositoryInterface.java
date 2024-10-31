package repository.photo;

import entities.Photo;

public interface PhotoRepositoryInterface {
	public Photo[] getAllByUser(int cod_user);
	public boolean create(Photo photo, int cod_user);
	public boolean update(Photo photo);
	public boolean delete(int cod_photo);
	
	public boolean updateNumberOfLikes(int cod_photo);
	
	public boolean incrementNumberOfLikes(int cod_photo);
	public boolean decrementNumberOfLikes(int cod_photo);
}
