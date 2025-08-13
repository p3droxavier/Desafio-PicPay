/* ANOT
 * 
 * AQUI FICA O CONTRoLADOR REST EM SPRING PARA A GERENCIA DE USUARIOS
 * 
 * */

package com.picpaysimplificado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.UserDTO;
import com.picpaysimplificado.services.UserService;

@RestController() 
@RequestMapping("/users")//END POINT /users
public class UserController {
	
	@Autowired
	private UserService userService;

	
	//END POINT PARA CRIAÇÃO DE USUÁRIO
	@PostMapping 
	public ResponseEntity<User> createUser(@RequestBody UserDTO user) { //INDICA QUE PRECISA INJETAR NO CORPO DA REQUISIÇÃO
		User newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	
	//END POINT DE LISTAGEM DE USUÁRIOS
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users =  this.userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
