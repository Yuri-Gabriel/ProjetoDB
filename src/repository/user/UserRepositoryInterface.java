package repository.user;

import entities.User;

public interface UserRepositoryInterface {
	public User[] getAll();
	public User get(String name);
	public boolean create(User user);
	public boolean update(User user);
	public boolean delete(int user_id);
}
