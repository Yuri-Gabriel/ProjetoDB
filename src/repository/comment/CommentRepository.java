package repository.comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Comment;
import entities.Photo;
import entities.User;
import repository.ConnectionDB;

public class CommentRepository implements CommentRepositoryInterface {
	
	private Connection conn = new ConnectionDB().startConnection();
	private Statement stm;
	private ResultSet result_query;
	
	public CommentRepository() {
		try {
			this.stm = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Comment[] getAllCommentByUser(int cod_user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment[] getAllCommentByPhoto(int cod_photo) {
		String query = String.format("SELECT cod_comment, text_comment, date_comment," + 
									" (SELECT name_user FROM user_entity WHERE user_entity.cod_user = comment_entity.cod_user)" + 
									" FROM comment_entity WHERE cod_photo = %d", cod_photo);
		Comment[] comments = null;
		int count = 0;
		try {
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				count++;
			}
			comments = new Comment[count];
			count = 0;
			this.result_query = this.stm.executeQuery(query);
			while(this.result_query.next()) {
				int id = Integer.parseInt(this.result_query.getString("cod_comment"));
				String text = this.result_query.getString("text_comment");
				String date = this.formatDate(this.result_query.getString("date_comment"));
				
				comments[count] = new Comment(id, text, date, null, null);
				
				count++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return comments;
	}

	@Override
	public boolean create(Comment comment, int cod_photo, int cod_user) {
		String query = String.format("INSERT INTO comment_entity (text_comment, date_comment, cod_photo, cod_user) VALUES ('%s', '%s', %d, %d)", comment.getText(), this.getAtualDate(), cod_photo, cod_user);
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}

	@Override
	public boolean delete(int cod_comment) {
		String query = String.format("DELETE FROM comment_entity WHERE cod_comment = %d", cod_comment);
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

}
