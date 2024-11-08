package main;

import entities.*;
import entities.dto.album.CreateAlbumDTO;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.photo.CreatePhotoDTO;
import entities.dto.user.CreateUserDTO;
import service.*;

public class App {

	public static void main(String[] args) throws Exception { //memory
		
		Comment[] comments = CommentService.getAllCommentByPhoto(-3);
		/*
		for(Comment c : comments) {
			System.out.println(c.toString());
			System.out.println("	" + c.getUser().toString());
		}
		*/
	}
}
