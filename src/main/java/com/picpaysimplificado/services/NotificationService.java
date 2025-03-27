/* ANOT COD
 * 
 * 'getStatusCode()' - SERVE PARA RETORNAR O CODIGO DE STATUS DE UMA RESPORTA HTTP
 * 
 * A CONFIGURAÇÃO DO 'restTemplate' ESTA EM 'infra->AppConfig'
 *    - SERVE PARA INDICAR QUAL É A CLASSE QUE É PRECISO INJETAR PARA AS OUTRAS CLASSES QUE ESTÃO DEPENDENDO DE 'restTemplate'
 *    
 *    
 * */

package com.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.picpaysimplificado.domain.transaction.TransactionRepository;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.NotificationDTO;

@Service
public class NotificationService {

    private final TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate;

    NotificationService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
	
	//PASSADNO USUÁRIO E UMA STRING QUE É A MENSAGEM(message)
	public void sendNotification(User user, String message)  throws Exception {
		//PRIMEIRO TERA DE PEGAR O EMAIL DO USUÁRIO
		String email = user.getEmail();
		
		//SETTANDO A NOTIFICAÇÃO PASSANDO O EMAIL DO USUARIO E A NOTIFICAÇÃO
		NotificationDTO notificationRequest = new NotificationDTO(email, message);
		
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v2/authorize", notificationRequest, String.class);
		
		
		//VERIFICAÇÃO DA RESPONSTA
		/* PELO FATO DE NÃO RETORNAR NADA FAZ-SE (SE NÃO FOR IGUAL)*/
		if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
			System.out.println("Erro ao enviar notificação!");
			throw new Exception("O Serviço de notificação esta fora do ar!");
		}
	}
	
}
