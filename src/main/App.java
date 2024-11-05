package main;

import entities.*;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.CreateUserDTO;
import service.*;

public class App {

	public static void main(String[] args) throws Exception { //memory
		UserService.create(new CreateUserDTO("jose", "", "jose1234", ""));
	}
}
