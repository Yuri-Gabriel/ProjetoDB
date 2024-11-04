package repository.user;

import entities.User;
import entities.dto.user.CreateUserDTO;
import entities.dto.user.UpdateUserDTO;

public interface UserRepositoryInterface {
	public User[] getAll();
	public User get(String email_user, String password_user);
	public boolean create(CreateUserDTO user);
	public boolean update(UpdateUserDTO user, int cod_user);
	public boolean delete(int user_id);
}
