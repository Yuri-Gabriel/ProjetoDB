package repository.user;

import entities.User;
import entities.dto.user.CreateUserDTO;
import entities.dto.user.UpdateUserDTO;
import repository.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements UserRepositoryInterface {
	
	private Connection conn = new ConnectionDB().startConnection();
	private Statement stm;
	private ResultSet result_query;
	
	public UserRepository() {
		try {
			this.stm = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User[] getAll() {
		User[] users = null;
		try {
			 this.result_query = this.stm.executeQuery("SELECT * FROM user_entity");
			 int cont = 0;
			 while(this.result_query.next()) {
				 cont++;
			 }
			 users = new User[cont];
			 cont = 0;
			 this.result_query = this.stm.executeQuery("SELECT * FROM user_entity");
			 while(this.result_query.next()) {
				 int id = Integer.parseInt(this.result_query.getString("cod_user"));
				 String name = this.result_query.getString("name_user");
				 String email = this.result_query.getString("email_user");
				 String password = this.result_query.getString("password_user");
				 String biography = this.result_query.getString("biography_user");
				 
				 users[cont] = new User(id, name, email, password, biography);
				 cont++;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User get(String email_user, String password_user) {
		User user = null;
		try {
			String query = String.format("SELECT * FROM user_entity WHERE email_user = '%s' AND password_user = '%s'", email_user, password_user);
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				int id = Integer.parseInt(this.result_query.getString("cod_user"));
				String name = this.result_query.getString("name_user");
				String email = this.result_query.getString("email_user");
				String password = this.result_query.getString("password_user");
				String biography = this.result_query.getString("biography_user");
				
				user = new User(id, name, email, password, biography);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean create(CreateUserDTO user) {
		String query = String.format("INSERT INTO user_entity (name_user, email_user, password_user, biography_user) VALUES ('%s', '%s', '%s', '%s')", user.getName(), user.getEmail(), user.getPassword(), user.getBiography());
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(UpdateUserDTO user, int cod_user) {
		String query = this.getUpdateQuery(user, cod_user);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int cod_user) {
		String query = String.format("DELETE FROM user_entity WHERE cod_user = '%d'", cod_user);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getUpdateQuery(UpdateUserDTO user, int cod_user) {
		String columns = "";
		if(user.getName() != "") {
			columns += String.format("name_user = '%s',", user.getName());
		}
		if(user.getEmail() != "") {
			columns += String.format("email_user = '%s',", user.getEmail());
		}
		if(user.getPassword() != "") {
			columns += String.format("password_user = '%s',", user.getPassword());
		}
		if(user.getBiography() != "") {
			columns += String.format("biography_user = '%s'", user.getBiography());
		}
		
		char[] columns_array = columns.toCharArray();
		if(columns_array[columns_array.length - 1] == ',') {
			columns_array[columns_array.length - 1] = ' ';
			columns = new String(columns_array).trim();
		}
		
		return String.format("UPDATE user_entity SET %s WHERE cod_user = '%s'", columns, cod_user);
	}
}
