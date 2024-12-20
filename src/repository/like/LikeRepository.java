package repository.like;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import repository.ConnectionDB;

public class LikeRepository implements LikeRepositoryInterface {
	
	private Connection conn = new ConnectionDB().startConnection();
	private Statement stm;
	
	public LikeRepository() {
		try {
			this.stm = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean giveOneLike(int cod_photo, int cod_user) {
		String query = String .format("INSERT INTO like_entity (cod_user, cod_photo) VALUES (%d, %d)", cod_user, cod_photo);
		try {
			this.stm.execute(query);
			this.updateNumberOfLikes(cod_photo);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	@Override
	public boolean ungiveOneLike(int cod_photo, int cod_user) {
		String query = String.format("DELETE FROM like_entity WHERE cod_user = %d AND cod_photo = %d", cod_user, cod_photo);
		try {
			this.stm.execute(query);
			this.updateNumberOfLikes(cod_photo);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	@Override
	public boolean toCheckIfUserAlreadGaveLike(int cod_photo, int cod_user) {
		String query = String.format("SELECT * FROM like_entity WHERE cod_photo = %d AND cod_user = %d", cod_photo, cod_user);
		try {
			ResultSet result = this.stm.executeQuery(query);
			if(result.next()) return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	private boolean updateNumberOfLikes(int cod_photo) {
		String query = String.format("CALL updateNumberOfLikes(%d);", cod_photo);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

}
