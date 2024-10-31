package main;

import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Comment;
import entities.Photo;
import entities.User;
import service.CommentService;
import service.LikeService;
import service.PhotoService;
import service.UserService;

public class App {

	public static void main(String[] args) throws Exception {
		
		//UserService.create(new User("joao", "joao1234@gmail.com", "joao123"));
		//CommentService.getAllCommentByPhoto(0);
		
		//CommentService.create(new Comment("muito bom", "", null, null), 7, 2);
		
		//CommentService.delete(1);
		Comment[] comments = CommentService.getAllCommentByPhoto(7);
		for(Comment c : comments) {
			System.out.println(c.toString());
		}
		
		
		
		User[] users = UserService.getAll();
		for(User u : users) {
			System.out.println(u.toString());
		}
		
		Photo[] photos = PhotoService.getAllByUser(1);
		for(Photo p : photos) {
			System.out.println(p.toString());
		}
	}
}
