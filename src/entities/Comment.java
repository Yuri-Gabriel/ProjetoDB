package entities;

import java.util.Date;

public class Comment {
	private String text;
	private Date date;
	private Photo photo;
	private User user;
	
	public Comment(String text, Date date, Photo photo, User user) {
		this.setText(text);
		this.setDate(date);
		this.setPhoto(photo);
		this.setUser(user);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
