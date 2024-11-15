package entities.dto.user;

public class UpdateUserDTO extends CreateUserDTO {

	public UpdateUserDTO(String name, String email, String password, String biography) {
		super(name, email, password, biography);
	}
}
