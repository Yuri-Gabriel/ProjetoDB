package entities.dto.comment;


public class CreateCommentDTO {
	private String text;
	private int cod_photo;
	private int cod_user;
	
	public CreateCommentDTO(String text, int cod_user, int cod_photo) {
		this.setText(text);
		this.setCod_user(cod_user);
		this.setCod_photo(cod_photo);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCod_photo() {
		return cod_photo;
	}
	public void setCod_photo(int cod_photo) {
		this.cod_photo = cod_photo;
	}
	public int getCod_user() {
		return cod_user;
	}
	public void setCod_user(int cod_user) {
		this.cod_user = cod_user;
	}
}
