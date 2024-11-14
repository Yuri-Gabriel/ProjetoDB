package main;

import entities.*;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.CreateUserDTO;
import service.*;

public class App {

	public static void main(String[] args) throws Exception { 
		UserService.create(new CreateUserDTO("walter", "waltinho123@gmail.com", "walwal1234", ""));
		
	}
}
