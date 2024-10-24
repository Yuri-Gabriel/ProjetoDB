package entities;

public class User {
	private String name;
	private String email;
	private String password;
	private String biography;
	
	public User(String name, String email, String password, String biography) {
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
}
