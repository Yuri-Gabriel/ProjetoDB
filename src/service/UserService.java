package service;

import entities.User;

import repository.user.UserRepository;

public class UserService {
	private static UserRepository repository = new UserRepository();
	
	public static User[] getAll() throws Exception {
		User[] users = repository.getAll();
		if(users == null) throw new Exception("Sem usuarios cadastrados");
		return users;
	}
	
	public static User get(String name) throws Exception {
		User user =  repository.get(name);
		if(user == null) throw new Exception("Usuario não encontrado");
		return user;
	}
	
	public static boolean create(User user) throws Exception {
		if(user == null) throw new Exception("Ausencia de dados");
		return repository.create(user);
	}
	
	public static boolean update(User user) throws Exception {
		if(user == null) throw new Exception("Ausencia de dados");
		return repository.update(user);
	}
	
	public static boolean delete(int cod_user) {
		return repository.delete(cod_user);
	}
}
