package entities;

public class Photo {
	private int id;
	private String name;
	private String description;
	private String upload_date;
	private Integer number_of_likes;
	private User user;
	
	public Photo(int id, String name, String description, String upload_date, int number_of_likes, User user) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setUpload_date(upload_date);
		this.setNumber_of_likes(number_of_likes);
		this.setUser(user);
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
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public Integer getNumber_of_likes() {
		return number_of_likes;
	}
	public void setNumber_of_likes(int number_of_likes) {
		this.number_of_likes = number_of_likes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d | name: %s | description: %s | upload_date: %s | number_of_likes: %d", this.getId(), this.getName(), this.getDescription(), this.getUpload_date(), this.getNumber_of_likes());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
