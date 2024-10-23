package entities.anime;

public class Season {
	private String title;
	private int number_of_episodes;
	private Episode[] episodes;
	
	public Season(String title, Episode[] episodes) {
		this.setTitle(title);
		this.setNumber_of_episodes(this.getEpisodes().length);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title_season) {
		this.title = title_season;
	}
	public int getNumber_of_episodes() {
		return number_of_episodes;
	}
	public void setNumber_of_episodes(int number_of_episodes) {
		this.number_of_episodes = number_of_episodes;
	}
	public Episode[] getEpisodes() {
		return episodes;
	}
	public void setEpisodes(Episode[] episodes) {
		this.episodes = episodes;
	}
	
}
