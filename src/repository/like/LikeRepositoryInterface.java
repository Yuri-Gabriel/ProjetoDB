package repository.like;

public interface LikeRepositoryInterface {
	public boolean giveOneLike(int cod_photo, int cod_user);
	public boolean ungiveOneLike(int cod_photo, int cod_user);
}
