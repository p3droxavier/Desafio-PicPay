/* ANOT COD
 * 'TransactionDTO' - RECEBE O PAYLOAD, REQUISIÇÃO DE POST(INFORMAÇÕES COLOCADAS NA REQUISIÇÃO)
 *       - RECEBENDO O PAYLOAD PARA POSTERIORMENTE FAZER A TRANSAÇÕES
 *      
 *  O PROPRIO DESAFIO FORNECE OS O SERVIÇO TERCEIRO VIA HTTP PARA O TESTE 
 *  
 *  'RestTemplate' - CLASSE OFERECIDA PELO SPRING PARA INTERAÇÕES HTTP
 *  
 *  '(String) authorizationResponse.getBody().get("message");' REALIZAÇÃO DE UM CASTING
 *       - FORÇA UM OBJETO DE UM TIPO PARA OUTRO TIPO, NESSE CASO DE UM OBJETO PARA STRING, PARA GUARDA-LO EM 'message'
 *       
 *  '.equalsIgnoreCase' - IGNORA MAIÚSCULO OU MINúSCULO
 *  
 * */

package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	//DECLARANDO DEPENDÊNCIAS
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	//REST TEMPLATE PARA FAZER A REQUISIÇÃO HTTP 
	@Autowired 	
	private RestTemplate restTemplate;
	
	//CHAMADA PARA notificationService 
	@Autowired
	private NotificationService notificationService;
	

	public Transaction createTransaction(TransactionDTO transaction) throws Exception {
		//BUSCA UM USUÁRIO PELO 'senderDocument' E O ARMAZENA NA VÁRIAVEL SENDER.
		User sender = this.userService.findUserByDocument(transaction.senderDocument()); //PELO ID DE QUEM ENVIOU(senderId)
		
		//BUSCA UM USUÁRIO PELO 'receiverDocument' E O ARMAZENA NA VÁRIAVEL REDEIVER.
		User receiver = this.userService.findUserByDocument(transaction.receiverDocument()); //PELO ID DE QUEM RECEBEU(receiverId)
		
		
		//VALIDAÇÃO DO USUÁRIO
		userService.validateTransaction(sender, transaction.value());
		
		 
		//SALVANDO A ALTORIZAÇÃO DA TRANSAÇÃO EM UM BOOLEAN
		boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
		
		//VERIFICAÇÃO DE TRANSAÇÃO AUTORIZADA (SE NÃO ESTIVER AUTORIZADO LANÇA UMA EXCEÇÃO)
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada! ");
		}
		 
		//CRIANDO NOVA TRANSAÇÃO NA TABELA
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		
		//ATUALIZAÇÃO DO SALDO DO USUÁRIO E SALVAR TRANSAÇÃO
		/* 1° - SUBTRAI O VALOR DA CONTA DO REMETENTE
		 * 2° - ADICIONA O VALOR DA TRANSAÇÃO AO SALDO DA CONTA DO DESTINATÁRIO 
		 */
		sender.setBalance(sender.getBalance().subtract(transaction.value())); 
		receiver.setBalance(receiver.getBalance().add(transaction.value())); 
		
		//SALVANDO NO BANCO DE DADOS 
		this.repository.save(newTransaction);
		this.userService.saveUser(sender); 
		this.userService.saveUser(receiver);
		
		//ENVIO DE NOTIFICAÇÃO 
		this.notificationService.sendNotification(sender, "Transação realizada com sucesso! ");
		this.notificationService.sendNotification(receiver, "Transação recebida com sucesso! ");
		
		return newTransaction;
	}
	
	//VERIFICA SE A TRANSAÇÃO ESTA AUTORIZADA POR MEIO DA REQUISIÇÃO HTTP 
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		try {
			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				@SuppressWarnings("unchecked") // Evita erro na compilação
				Map<String, Object> body = response.getBody();
				
				if(body != null && body.containsKey("data")) {
					@SuppressWarnings("unchecked")
					Map<String, Object> data = (Map<String, Object>) body.get("data");
					
					if(data != null && data.containsKey("authorization")) {
						Boolean isAuthorized = (Boolean) data.get("authorization");
						return Boolean.TRUE.equals(isAuthorized);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Erro na Requisição de autorização: " + e.getMessage());
		}
		
		return false;
	}
}


























