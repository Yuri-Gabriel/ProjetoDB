package entities.anime;

public class Studio {
	private String name;
	private String description;
	private double score;
	
	public Studio(String name, String description, double score) {
		this.setName(name);
		this.setDescription(description);
		this.setScore(score);
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
