package service;

import entities.User;
import entities.dto.user.CreateUserDTO;
import entities.dto.user.UpdateUserDTO;
import repository.user.UserRepository;

public class UserService {
	private static UserRepository repository = new UserRepository();
	
	public static User[] getAll() throws Exception {
		User[] users = repository.getAll();
		try {
			if(users == null) throw new Exception("ERRO (UserService.getAll):\nSem usuarios cadastrados");
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return users;
	}
	
	public static User get(String email_user, String password_user) {
		User user = null;
		try {
			if(email_user == "") throw new Exception("ERRO (UserService.get):\nO campo email_user não foi informado");
			if(password_user == "") throw new Exception("ERRO (UserService.get):\nO campo password_user não foi informado");
			
			user = repository.get(email_user, password_user);
			
			if(user == null) throw new Exception("ERRO (UserService.get):\nUsuario não encontrado");
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return user;
	}
	
	public static boolean create(CreateUserDTO user) {
		try {
			if(user == null) throw new Exception("ERRO (UserService.create):\nA entidade CreateUserDTO não foi enviada");
			
			if(user.getName() == "") throw new Exception("ERRO (UserService.create):\nO atributo name da entidade CreateUserDTO não foi informado");
			if(user.getEmail() == "") throw new Exception("ERRO (UserService.create):\nO atributo email da entidade CreateUserDTO não foi informado");
			if(user.getPassword() == "") throw new Exception("ERRO (UserService.create):\nO atributo password da entidade CreateUserDTO não foi informado");
			
			return repository.create(user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	public static boolean update(UpdateUserDTO user, int cod_user) {
		try {
			if(user == null) throw new Exception("ERRO (UserService.update):\nA entitdade UpdateUserDTO não foi enviada");
			
			if(cod_user < 0) throw new Exception("ERRO (UserService.update):\nO código identificador (cod_user) informado é inválido");
			
			return repository.update(user, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	public static boolean delete(int cod_user) {
		try {
			if(cod_user < 0) throw new Exception("ERRO (UserService.delete):\nO código identificador (cod_user) informado é inválido");
			return repository.delete(cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
