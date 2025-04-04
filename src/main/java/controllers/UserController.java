package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.UserDTO;
import com.picpaysimplificado.services.UserService;

@RestController("/users") //END POINT /users
public class UserController {
	
	@Autowired
	private UserService userServise;

	@PostMapping //END POINT PARA CRIAÇÃO DE USUÁRIO
	public ResponseEntity<User> createUser(UserDTO user) {
		User newUser = userServise.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	//END POINT DE LISTAGEM DE USUÁRIOS
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return this.userServise.getAllUsers();
	}
}
