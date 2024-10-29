package main;



import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Photo;
import entities.User;
import service.PhotoService;
import service.UserService;

public class App {

	public static void main(String[] args) {
		
		Photo[] photos = PhotoService.getAllByUser(1);
		for(Photo p : photos) {
			System.out.println(p.toString());
		}
	}
}
