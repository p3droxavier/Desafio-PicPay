/* ANOT COD
 * 
 * '@Autowired' - É USADA PARA INJEÇÃO AUTOMÁTICA DE DEPENDÊNCIAS.
 *      INJETA UMA INSTANCIA AUTOMATICAMENTE 'UserRepository' NA CLASSE 'UserService'
 * 
 * '.compareTo' - COMPARA VALORES E RETORNA 0, 1 OU -1
 *      NESSE CASO 'if(sender.getBalance().compareTo(amount) < 0)' SE O RETORNO FOR MENOR QUE 0, ALGO ACONTECE
 *      - SE OS DOIS VALORES FOREM IGUAIS IRA RETORNAR 0
 *      - SE O VALOR DE 'sender.getBalane()'->"SALDO" FOR MAIOR QUE 'amount'->"QUANTIA" IRA RETORNAR 1
 *      - SE O VALOR DE 'sender.getBalane()'->"SALDO" FOR MENOR QUE 'amount'->"QUANTIA" IRA RETORNAR -1
 *      
 *      
 *  '() throws Exception {...}' - É USADO PARA ADIAR A CRIÇÃO DA EXCEÇÃO ATÉ QUE ELA REALMENTE SEJA NECESSÁRIA
 *  
 *  'return this.repository.findById(id).orElseThrow(() -> new Exception(" "))' - 
 *       - 'findById(id)' - REOTRNA UM 'Optional<>'QUE PODE CONTER O USUARIO ENCONTRADO OU ESTAR VAZIO
 *       - '.orElseThrow(() -> new Exception(" ")' SE O OPTIONAL ESTIVER VAZIO (SE O USUARIO NÃO FOR ENCONTRADO) O METODO 
 *         '.orElseThrow' LANÇA UM EXCEÇÃO USANDO UMA ESPRESSÃO LAMBDA QUE CRIA UMA NOVA INSTÂNCIA DE 'Exception' COM A MENSAGEM.
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
	
	//METODO DE VALIDAÇÃO
	public void validateTransaction(User sender, BigDecimal amount) throws Exception{
		
		//VERIFCA O NIVEL DE QUEM ENVIO
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuário do tipo logista não está autorizado a realizar transação! ");
		}
		
		//VERIFICA O SALDO DE QUEM ENVIOU
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo suficiente! ");
		}
	}
	
	//CLASSE PARA MANIPULAÇÃO DE USUARIOS INDEPENDENTE DO 'UserRepository'
	public User findUserById(Long id) throws Exception {
		return this.repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado! "));
	}
	
	//CRIAÇÃO E SALVAMENTO DO USUAÁRIO, DO ESTADO DO SEU BALANCE
	public void saveUser(User user) {
		this.repository.save(user);
	}

}
