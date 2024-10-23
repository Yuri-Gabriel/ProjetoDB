package entities.manga;

import java.util.Date;

public class Manga {
	private Date release_date;
	private int number_of_chapters;
	private Chapter[] chapters;
	
	public Manga(Date relesase_date, int number_of_chapters, Chapter[] chapters) {
		this.setRelease_date(relesase_date);
		this.setNumber_of_chapters(number_of_chapters);
		this.setChapters(chapters);
	}
	
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public int getNumber_of_chapters() {
		return number_of_chapters;
	}
	public void setNumber_of_chapters(int number_of_chapters) {
		this.number_of_chapters = number_of_chapters;
	}

	public Chapter[] getChapters() {
		return chapters;
	}

	public void setChapters(Chapter[] chapters) {
		this.chapters = chapters;
	}
}
