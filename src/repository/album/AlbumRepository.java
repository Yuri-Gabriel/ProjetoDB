package repository.album;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Album;
import entities.Photo;
import repository.ConnectionDB;

public class AlbumRepository implements AlbumRepositoryInterface {
	
	private Connection conn = new ConnectionDB().startConnection();
	private Statement stm;
	private ResultSet result_query;
	
	public AlbumRepository() {
		try {
			this.stm = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Album[] getAllAlbumByUser(int cod_user) {
		Photo[] photos = null;
		Album[] albums = null;
		ResultSet result_query_photos = null;
		Statement stm_photo = null;
		try {
			stm_photo = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = String.format("SELECT * FROM album_entity WHERE cod_user = %d", cod_user);
		int count = 0;
		int count_photo = 0;
		try {
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				count++;
			}
			albums = new Album[count];
			count = 0;
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				
				int id = Integer.parseInt(this.result_query.getString("cod_album"));
				String name_album = this.result_query.getString("name_album");
				String description_album = this.result_query.getString("description_album");
				String creation_date_album = this.formatDate(this.result_query.getString("creation_date_album"));
				
				albums[count] = new Album(id, name_album, description_album, creation_date_album);
				result_query_photos = stm_photo.executeQuery(String.format("SELECT pe.name_photo, pe.description_photo, pe.data_upload_photo, pe.number_of_likes_photo FROM photo_entity pe, album_photo ap WHERE ap.cod_album = %d AND ap.cod_photo = pe.cod_photo", id));
				while(result_query_photos.next()) {
					count_photo++;
				}
				photos = new Photo[count_photo];
				count_photo = 0;
				result_query_photos = stm_photo.executeQuery(String.format("SELECT pe.name_photo, pe.description_photo, pe.data_upload_photo, pe.number_of_likes_photo FROM photo_entity pe, album_photo ap WHERE ap.cod_album = %d AND ap.cod_photo = pe.cod_photo", id));
				while(result_query_photos.next()) {
					String name_photo = result_query_photos.getString("name_photo");
					String description_photo = result_query_photos.getString("description_photo");
					String data_upload_photo = this.formatDate(result_query_photos.getString("data_upload_photo"));
					int number_of_likes_photo = Integer.parseInt(result_query_photos.getString("number_of_likes_photo"));
					
					photos[count_photo] = new Photo(name_photo, description_photo, data_upload_photo, number_of_likes_photo);
					count_photo++;
				}
				albums[count].setPhotos(photos);
				count++;
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return albums;
	}

	@Override
	public boolean create(Album album, int cod_user) {
		String query = String.format("INSERT INTO album_entity (name_album, description_album, creation_date_album, cod_user) VALUES ('%s', '%s', '%s')", album.getName(), album.getDescription(), this.getAtualDate(), cod_user);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addPhoto(int cod_album, int cod_photo) {
		String query = String.format("INSERT INTO album_photo (cod_album, cod_photo) VALUES (%d, %d)", cod_album, cod_photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int cod_album) {
		try {
			this.stm.execute("DELETE FROM album_photo WHERE cod_album = " + cod_album);
			this.stm.execute("DELETE FROM album_entity WHERE cod_album = " + cod_album);
		} catch (SQLException err) {
			err.printStackTrace();
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

}
