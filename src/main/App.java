package main;



import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.User;
import service.UserService;

public class App {

	public static void main(String[] args) throws Exception {
		User user1 = new User("cleiton", "cleiton@gmail.com", "latin", "sou um rapaz muito legal");
		UserService.create(user1);
		
		User[] users = UserService.getAll();
		for(User user : users) {
			System.out.println(user.toString());
		}
	}
}
