package entities;

import entities.anime.Anime;
import entities.manga.Manga;

public class Work {
	private String name;
	private String sinpse;
	private Category[] category;
	private Anime anime;
	private Manga manga;
	private Author author;
	
	public Work(String name, String sinopse, Category[] category,
				Anime anime, Manga manga, Author author) {
		this.setName(name);
		this.setSinpse(sinopse);
		this.setCategory(category);
		this.setAnime(anime);
		this.setManga(manga);
		this.setAuthor(author);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinpse() {
		return sinpse;
	}

	public void setSinpse(String sinpse) {
		this.sinpse = sinpse;
	}

	public Category[] getCategory() {
		return category;
	}

	public void setCategory(Category[] category) {
		this.category = category;
	}

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
}
