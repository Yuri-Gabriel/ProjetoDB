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
		User user = null;
		try {
			if(email_user.trim() == "") throw new Exception("Campo email não foi informado");
			if(password_user.trim() == "") throw new Exception("Campo senha não foi informado");
			user = repository.get(email_user, password_user);
			if(user == null) throw new Exception("Usuario não encontrado");
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return user;
	}
	
	public static boolean create(CreateUserDTO user) throws Exception {
		try {
			if(user == null) throw new Exception("Ausencia de dados");
			
			if(user.getName() == "") throw new Exception("Campo nome não foi informado");
			if(user.getEmail() == "") throw new Exception("Campo email não foi informado");
			if(user.getPassword() == "") throw new Exception("Campo senha não foi informado");
			
			return repository.create(user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	public static boolean update(UpdateUserDTO user, int cod_user) throws Exception {
		try {
			if(user == null) throw new Exception("Ausencia de dados");
			
			if(cod_user < 0) throw new Exception("Código identificador inválido");
			
			return repository.update(user, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	public static boolean delete(int cod_user) {
		try {
			if(cod_user < 0) throw new Exception("Codigo identificador inválido");
			return repository.delete(cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
