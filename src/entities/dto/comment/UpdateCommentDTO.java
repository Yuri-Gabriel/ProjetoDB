package entities.dto.comment;

public class UpdateCommentDTO {
	
	private String text;
	
	public UpdateCommentDTO(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
