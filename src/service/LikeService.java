package service;

import repository.like.LikeRepository;

public class LikeService {
	private static LikeRepository repository = new LikeRepository();
	
	public static boolean giveOneLike(int cod_photo, int cod_user) {
		try {
			if(cod_photo < 0 || cod_user < 0) throw new Exception("Dados inválidos");
			return repository.giveOneLike(cod_photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
		
	}
	public static boolean ungiveOneLike(int cod_photo, int cod_user) {
		try {
			if(cod_photo < 0 || cod_user < 0) throw new Exception("Dados inválidos");
			return repository.ungiveOneLike(cod_photo, cod_user);
		} catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
		return false;
	}
}
