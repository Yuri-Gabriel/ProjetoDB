package main;

import entities.User;
import service.UserService;

public class App {

	public static void main(String[] args) throws Exception {
		User[] users = UserService.getAll();
		for(User user : users) {
			System.out.println(user.toString());
		}
		
		User user1 = UserService.get("yuri");
		System.out.println("\n" + user1.toString());
		
		//User user2 = new User("marcos", "marcos1234@yahoo.com", "marcos1010");
		//UserService.create(user2);
		
		User user3 = new User(10, "", "", "marquin1945", "");
		UserService.update(user3);
		
		UserService.delete(2);
		
		
	}

}
