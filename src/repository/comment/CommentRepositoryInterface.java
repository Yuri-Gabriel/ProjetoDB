package repository.comment;

import entities.Comment;
import entities.dto.comment.CreateCommentDTO;
import entities.dto.comment.UpdateCommentDTO;

public interface CommentRepositoryInterface {
	public Comment[] getAllCommentByPhoto(int cod_photo);
	public boolean create(CreateCommentDTO comment);
	public boolean update(UpdateCommentDTO comment, int cod_comment);
	public boolean delete(int cod_comment);
}
