package entities;

import java.util.Date;

public class Photo {
	private String name;
	private String description;
	private Date upload_date;
	private int number_of_likes;
	
	public Photo(String name, String description, Date upload_date, int number_of_likes) {
		this.setName(name);
		this.setDescription(description);
		this.setUpload_date(upload_date);
		this.setNumber_of_likes(number_of_likes);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public int getNumber_of_likes() {
		return number_of_likes;
	}
	public void setNumber_of_likes(int number_of_likes) {
		this.number_of_likes = number_of_likes;
	}
}
