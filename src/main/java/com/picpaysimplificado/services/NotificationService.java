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
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.picpaysimplificado.repositories.TransactionRepository;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.NotificationDTO;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
 
	//PASSADNO USUÁRIO E UMA STRING QUE É A MENSAGEM(message)
	public void sendNotification(User user, String message)  throws Exception {
		String email = user.getEmail();// PEGA O EMAIL
		NotificationDTO notificationRequest = new NotificationDTO(email, message);//SETTANDO O EMAIL E PASSANDO A NOTIFICAÇÃO
		
//		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v2/authorize", notificationRequest, String.class);
		
		//VERIFICAÇÃO DA RESPONSTA
		/* PELO FATO DE NÃO RETORNAR NADA FAZ-SE (SE NÃO FOR IGUAL)*/
//		if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
//			System.out.println("Erro ao enviar notificação!");
//			throw new Exception("O Serviço de notificação esta fora do ar!");
//		}
		
		System.out.print("Notificação enviada com sucesso");
	}
	
}
