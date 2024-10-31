package repository.photo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Photo;
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
				
				photos[cont] = new Photo(id, name, description, date, number_of_likes);
				
				cont++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return photos.length == 0 ? null : photos;
	}

	@Override
	public boolean create(Photo photo, int cod_user) {
		String query = String.format("INSERT INTO photo_entity (name_photo, description_photo, data_upload_photo, number_of_likes_photo, cod_user) VALUES ('%s', '%s', '%s', '%d', '%d')", photo.getName(), photo.getDescription(), this.getAtualDate(), 0, cod_user);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Photo photo) {
		String query = this.getUpdateQuery(photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int cod_photo) {
		String query = String.format("DELETE FROM photo_entity WHERE cod_photo = '%d'", cod_photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			//"Não existe nunhuma foto cadastrada com o codigo identificador informado"
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	@Override
	public boolean updateNumberOfLikes(int cod_photo) {
		String query = String.format("UPDATE photo SET number_of_likes_photo = (SELECT COUNT(*) FROM like_entity WHERE cod_photo = %d) WHERE cod_photo = %d", cod_photo, cod_photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	@Override
	public boolean incrementNumberOfLikes(int cod_photo) {
		//UPDATE photo_entity SET number_of_likes_photo = number_of_likes_photo + 1 WHERE cod_photo = 7;
		String query = String.format("UPDATE photo_entity SET number_of_likes_photo = number_of_likes_photo + 1 WHERE cod_photo = '%d'", cod_photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	@Override
	public boolean decrementNumberOfLikes(int cod_photo) {
		String query = String.format("UPDATE photo_entity SET number_of_likes_photo = number_of_likes_photo - 1 WHERE cod_photo = '%d'", cod_photo);
		try {
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
	
	private String getUpdateQuery(Photo photo) {
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
		
		return String.format("UPDATE user_entity SET %s WHERE cod_user = '%s'", columns, photo.getId());
	}

	

	

}
