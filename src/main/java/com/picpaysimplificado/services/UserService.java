/* ANOT COD
 * 
 * '@Autowired' - É USADA PARA INJEÇÃO AUTOMÁTICA DE DEPENDÊNCIAS.
 *      INJETA UMA INSTANCIA AUTOMATICAMENTE 'UserRepository' NA CLASSE 'UserService'
 * 
 * */

package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception{
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuario do tipo logista não está autorizado a realizar transação!");
		}
	}

}
