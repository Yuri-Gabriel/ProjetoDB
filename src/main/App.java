package main;

import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Album;
import entities.Comment;
import entities.Photo;
import entities.User;
import service.AlbumService;
import service.CommentService;
import service.LikeService;
import service.PhotoService;
import service.UserService;

public class App {

	public static void main(String[] args) throws Exception {
		
		Album[] albums = AlbumService.getAllAlbumByUser(1);
		
		for(Album a : albums) {
			System.out.println(a.toString());
			for(Photo p : a.getPhotos()) {
				System.out.println("     " + p.toString());
			}
		}
	}
}
