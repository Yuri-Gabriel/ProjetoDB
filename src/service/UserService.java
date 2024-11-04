package service;

import entities.User;
import entities.dto.user.CreateUserDTO;
import entities.dto.user.UpdateUserDTO;
import repository.user.UserRepository;

public class UserService {
	private static UserRepository repository = new UserRepository();
	
	public static User[] getAll() throws Exception {
		User[] users = repository.getAll();
		if(users == null) throw new Exception("Sem usuarios cadastrados");
		return users;
	}
	
	public static User get(String email_user, String password_user) throws Exception {
		User user =  repository.get(email_user, password_user);
		if(user == null) throw new Exception("Usuario não encontrado");
		return user;
	}
	
	public static boolean create(CreateUserDTO user) throws Exception {
		if(user == null) throw new Exception("Ausencia de dados");
		return repository.create(user);
	}
	
	public static boolean update(UpdateUserDTO user, int cod_user) throws Exception {
		if(user == null) throw new Exception("Ausencia de dados");
		return repository.update(user, cod_user);
	}
	
	public static boolean delete(int cod_user) {
		return repository.delete(cod_user);
	}
}
