package main;

import java.util.Scanner;

import entities.Album;
import entities.Comment;
import entities.Photo;
import entities.User;
import service.AlbumService;
import service.CommentService;
import service.PhotoService;
import service.UserService;
import view.IndexView;
import entities.*;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.CreateUserDTO;
import service.*;


public class App {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception { 
		IndexView.main();
	}
}
