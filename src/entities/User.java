package entities;

public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String biography;
	
	public User(Integer id, String name, String email, String password, String biography) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setBiography(biography);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d | name: %s | email: %s | password: %s | biography: %s", this.getId(), this.getName(), this.getEmail(), this.getPassword(), this.getBiography());
	}
}
