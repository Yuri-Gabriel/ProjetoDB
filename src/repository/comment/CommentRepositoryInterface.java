package repository.comment;

import entities.Comment;

public interface CommentRepositoryInterface {
	public Comment[] getAllCommentByUser(int cod_user);
	public Comment[] getAllCommentByPhoto(int cod_photo);
	public boolean create(Comment comment, int cod_photo, int cod_user);
	public boolean delete(int cod_comment);
}
