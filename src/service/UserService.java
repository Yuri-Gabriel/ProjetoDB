package service;

import entities.User;

import repository.user.UserRepository;

public class UserService {
	private static UserRepository repository = new UserRepository();
	
	public static User[] getAll() {
		return repository.getAll();
	}
	
	public static User get(String name) throws Exception {
		User user =  repository.get(name);
		if(user == null) throw new Exception("Usuario não encontrado");
		return user;
	}
	
	public static void create(User user) throws Exception {
		if(user == null) throw new Exception("Ausencia de dados");
		repository.create(user);
	}
	
	public static void update(User user) throws Exception {
		if(user == null) throw new Exception("Ausencia de dados");
		repository.update(user);
	}
	
	public static void delete(int cod_user) {
		repository.delete(cod_user);
	}
}
