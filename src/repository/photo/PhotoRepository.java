package repository.photo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entities.Photo;
import entities.User;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.photo.UpdatePhotoDTO;
import repository.ConnectionDB;

public class PhotoRepository implements PhotoRepositoryInterface  {
	
	private Connection conn = new ConnectionDB().startConnection();
	private Statement stm;
	private ResultSet result_query;
	
	public PhotoRepository() {
		try {
			this.stm = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Photo[] getAll() {
		Photo[] photos = null;
		String query = "SELECT * FROM getallphotos";
		
		try {
			this.result_query = this.stm.executeQuery(query);
			int cont = 0;
			while(this.result_query.next()) {
				cont++;
			}
			photos = new Photo[cont];
			cont = 0;
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				int id = Integer.parseInt(this.result_query.getString("cod_photo"));
				String name_photo = this.result_query.getString("name_photo");
				String description = this.result_query.getString("description_photo");
				String date = this.formatDate(this.result_query.getString("date_upload_photo"));
				int number_of_likes = Integer.parseInt(this.result_query.getString("number_of_likes_photo"));
				int cod_user = Integer.parseInt(this.result_query.getString("cod_user"));
				String name_user = this.result_query.getString("name_user");
				String email_user = this.result_query.getString("email_user");
				
				Photo photo = new Photo(id, name_photo, description, date, number_of_likes, new User(cod_user, name_user, email_user, null, null));
				
				photos[cont] = photo;
				
				cont++;
				
			}
			
		} catch (SQLException err) {
			System.out.println(err.toString());
		}
		return photos;
	}

	@Override
	public Photo[] getAllByUser(int cod_user) {
		Photo[] photos = null;
		String query = String.format("SELECT * FROM photo_entity WHERE cod_user = '%d'", cod_user);
		try {
			this.result_query = this.stm.executeQuery(query);
			int cont = 0;
			while(this.result_query.next()) {
				cont++;
			}
			photos = new Photo[cont];
			cont = 0;
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				int id = Integer.parseInt(this.result_query.getString("cod_photo"));
				String name = this.result_query.getString("name_photo");
				String description = this.result_query.getString("description_photo");
				String date = this.formatDate(this.result_query.getString("date_upload_photo"));
				int number_of_likes = Integer.parseInt(this.result_query.getString("number_of_likes_photo"));
				
				photos[cont] = new Photo(id, name, description, date, number_of_likes, null);
				
				cont++;
			}
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return photos.length == 0 ? null : photos;
	}

	@Override
	public boolean create(CreatePhotoDTO photo, int cod_user) {
		String query = String.format("INSERT INTO photo_entity (name_photo, description_photo, date_upload_photo, number_of_likes_photo, cod_user) VALUES ('%s', '%s', '%s', '%d', '%d')", photo.getName(), photo.getDescription(), this.getAtualDate(), 0, cod_user);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return false;
	}

	@Override
	public boolean update(UpdatePhotoDTO photo, int cod_photo) {
		String query = this.getUpdateQuery(photo, cod_photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(int cod_photo) {
		String query = String.format("DELETE FROM photo_entity WHERE cod_photo = '%d'", cod_photo);
		try {
			this.stm.execute(String.format("DELETE FROM comment_entity WHERE cod_photo = '%d'", cod_photo));
			this.stm.execute(String.format("DELETE FROM album_photo WHERE cod_photo = '%d'", cod_photo));
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	private String getAtualDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String formatDate(String date) {
		Date dt = null;
		try {
			dt = new SimpleDateFormat("yyyyMMdd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(dt);
	}
	
	private String getUpdateQuery(UpdatePhotoDTO photo, int cod_photo) {
		String columns = "";
		if(photo.getName() != "") {
			columns += String.format("name_photo = '%s',", photo.getName());
		}
		if(photo.getDescription() != "") {
			columns += String.format("description_photo = '%s'", photo.getDescription());
		}
		
		char[] columns_array = columns.toCharArray();
		if(columns_array[columns_array.length - 1] == ',') {
			columns_array[columns_array.length - 1] = ' ';
			columns = new String(columns_array).trim();
		}
		
		return String.format("UPDATE photo_entity SET %s WHERE cod_user = '%s'", columns, cod_photo);
	}

	

	

}
