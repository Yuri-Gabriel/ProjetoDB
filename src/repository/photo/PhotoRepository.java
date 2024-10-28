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
		String query = String.format("SELECT * FROM photo_entity WHERE cod_user = '%d'", cod_user);
		try {
			this.result_query = this.stm.executeQuery(query);
			int cont = 0;
			while(this.result_query.next()) {
				cont++;
			}
			Photo[] photos = new Photo[cont];
			cont = 0;
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				int id = Integer.parseInt(this.result_query.getString("cod_photo"));
				String name = this.result_query.getString("name_photo");
				String description = this.result_query.getString("descripton_photo");
				String date = this.formatDate(this.result_query.getString("date_upload_photo"));
				int number_of_likes = Integer.parseInt(this.result_query.getString("number_of_likes_photo"));
				
				photos[cont] = new Photo(id, name, description, date, number_of_likes);
				
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Photo get(int cod_photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Photo photo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Photo photo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int cod_photo) {
		// TODO Auto-generated method stub
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
			dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(dt);
	}

}
