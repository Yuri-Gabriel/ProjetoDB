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
import entities.dto.comment.CreateCommentDTO;
import entities.dto.comment.UpdateCommentDTO;
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
	public Comment[] getAllCommentByPhoto(int cod_photo) {
		String query = String.format("SELECT cod_comment, text_comment, date_comment, name_user, email_user FROM comment_entity "
									+ "INNER JOIN user_entity "
									+ "ON comment_entity.cod_user = user_entity.cod_user "
									+ "WHERE cod_photo = %d;", cod_photo);
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
				String name_user = this.result_query.getString("name_user");
				String email_user = this.result_query.getString("email_user");
				
				comments[count] = new Comment(id, text, date, null, new User(null, name_user, email_user, null, null));
				
				count++;
				
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return comments;
	}

	@Override
	public boolean create(CreateCommentDTO comment) {
		String query = String.format("INSERT INTO comment_entity (text_comment, date_comment, cod_photo, cod_user) VALUES ('%s', '%s', %d, %d)", comment.getText(), this.getAtualDate(), comment.getCod_photo(), comment.getCod_user());
		try {
			this.stm.execute(query);
			return true;
		} catch (SQLException err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
	
	@Override
	public boolean update(UpdateCommentDTO comment, int cod_comment) {
		String query = String.format("UPDATE comment_entity SET text_comment = '%s' WHERE cod_comment = %d", comment.getText(), cod_comment);
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
