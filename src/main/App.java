package main;

import entities.*;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.CreateUserDTO;
import service.*;

public class App {

	public static void main(String[] args) throws Exception { //memory
		
		//CommentService.create(new CreateCommentDTO("catapimbas", 1, 9));
		Comment[] comments = CommentService.getAllCommentByPhoto(9);
		System.out.println(comments);
		for(Comment c : comments) {
			System.out.println(c.toString());
		}
	}
}
