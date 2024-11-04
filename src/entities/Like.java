package entities;

public class Like {
	private Photo photo;
	private User user;
	
	public Like(Photo photo, User user) {
		this.setPhoto(photo);
		this.setUser(user);
	}
	
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
