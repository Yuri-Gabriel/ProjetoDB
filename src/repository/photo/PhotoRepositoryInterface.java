package repository.photo;

import entities.Photo;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.photo.UpdatePhotoDTO;

public interface PhotoRepositoryInterface {
	public Photo[] getAll();
	public Photo[] getAllByUser(int cod_user);
	public boolean create(CreatePhotoDTO photo, int cod_user);
	public boolean update(UpdatePhotoDTO photo, int cod_photo);
	public boolean delete(int cod_photo);
}
