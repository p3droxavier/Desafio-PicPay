/* ANOT COD
 * 
 * 'Optional<User> findUserByDocument(String document);' - METODO QUE BUSCA UM USUARIO PELO DOCUMENTO 
 *      'Optional<User>' - RETORNA UM OBJETO OPCIONAL QUE PODE CONTER UM 'USER' OU ESTAR FAZIA.
 *      '(String document)' - PARAMETRO QUE REPRESENTA O DOCUMENTO USADO NA BUSCA.
 * 
 * OBS: O JPA FAZ UMA BUSCA INTELIGENTE, ONDE AO PEGAR 'findUserByDocument' ELE ENTENDE QUE PRECISA 'ENCONTRAR USUARIO PELO DOCUMENTO'.
 * */

package com.picpaysimplificado.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.domain.user.User;

//PASSANDO DENTRO DE <> QUAL TABELA A SER MANIPULADA, E QUAL O TIPO DO ID
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findUserByDocument(String document);
	
	Optional<User> findUserById(Long id);
}
