package repository.user;

import entities.User;

public interface UserRepositoryInterface {
	public User[] getAll();
	public User get(String name);
	public void create(User user);
	public void update(User user);
	public void delete(int user_id);
}
