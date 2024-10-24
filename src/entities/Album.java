package entities;

import java.util.Date;

public class Album {
	private String name;
	private String description;
	private Date creation_date;
	private Photo[] photos;
	
	public Album(String name, String description, Date creation_date) {
		this.setName(name);
		this.setDescription(description);
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
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Photo[] getPhotos() {
		return photos;
	}
	public void setPhotos(Photo[] photos) {
		this.photos = photos;
	}
}
