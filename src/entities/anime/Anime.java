package entities.anime;

import java.util.Date;

public class Anime {
	private Date release_date;
	private int number_of_season;
	private int number_of_episodes;
	private Studio[] studios;
	
	public Anime(Date release_date, int number_of_seasons,
			int number_of_episodes, Studio[] studios) {
		this.setRelease_date(release_date);
		this.setNumber_of_season(number_of_seasons);
		this.setNumber_of_episodes(number_of_episodes);
		this.setStudios(studios);
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getNumber_of_season() {
		return number_of_season;
	}

	public void setNumber_of_season(int number_of_season) {
		this.number_of_season = number_of_season;
	}

	public int getNumber_of_episodes() {
		return number_of_episodes;
	}

	public void setNumber_of_episodes(int number_of_episodes) {
		this.number_of_episodes = number_of_episodes;
	}

	public Studio[] getStudios() {
		return studios;
	}

	public void setStudios(Studio[] studios) {
		this.studios = studios;
	}
	
	
}
