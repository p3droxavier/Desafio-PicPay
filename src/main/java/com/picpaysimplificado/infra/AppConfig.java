/* ANOT COD
 * 
 * AQUI CRIA-SE O METODO QUE INDICA A INSTANCIA A SER INJETADA
 * 
 * O '@Bean' TEM O TRABALHO DE FAZER AS INJEÇÕES ADEQUADASNAS NAS CLASSES QUE DEMPENDEM DO 'RestTemplate'
 * 
 * */

package com.picpaysimplificado.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
