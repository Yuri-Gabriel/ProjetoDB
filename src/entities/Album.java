package entities;

public class Album {
<<<<<<< HEAD
	private Integer id;
=======
	private int id;
>>>>>>> bd1d6228ab1213ef626083c538174680c11198f9
	private String name;
	private String description;
	private String creation_date;
	private Photo[] photos;
	
	public Album(int id, String name, String description, String creation_date) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setCreation_date(creation_date);
<<<<<<< HEAD
	}
	
	public Album(String name, String description, String creation_date) {
		this.setName(name);
		this.setDescription(description);
		this.setCreation_date(creation_date);
=======
>>>>>>> bd1d6228ab1213ef626083c538174680c11198f9
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
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public Photo[] getPhotos() {
		return photos;
	}
	public void setPhotos(Photo[] photos) {
		this.photos = photos;
	}
<<<<<<< HEAD

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	@Override
	public String toString() {
		return String.format("id: %d | name: %s ", this.getId(), this.getName());
	}
=======
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d | name: %s | desc: %s | date: %s", this.getId(), this.getName(), this.getDescription(),this.getCreation_date());
	}
	
>>>>>>> bd1d6228ab1213ef626083c538174680c11198f9
}
