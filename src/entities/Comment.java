package entities;

import java.util.Date;

public class Comment {
	private Integer id = null;
	private String text;
	private String date;
	private Photo photo;
	private User user;
	
	public Comment(int id, String text, String date, Photo photo, User user) {
		this.setId(id);
		this.setText(text);
		this.setDate(date);
		this.setPhoto(photo);
		this.setUser(user);
	}
	
	public Comment(String text, String date, Photo photo, User user) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("id: %d | text: %s | date: %s", this.getId(), this.getText(), this.getDate());
	}
}
