package main;

import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Photo;
import entities.User;
import service.LikeService;
import service.PhotoService;
import service.UserService;

public class App {

	public static void main(String[] args) throws Exception {
		
		//PhotoService.create(new Photo("teste", "testando", "", 0), 1);
		
		//LikeService.giveOneLike(1, 3);
		//LikeService.giveOneLike(1, 4);
		
		LikeService.ungiveOneLike(1, 4);
		
		LikeService.updateNumberOfLikes(1);
		
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
