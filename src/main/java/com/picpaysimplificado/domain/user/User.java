/* ANOT COD
 * '@Entity' -> REPRESENTA UMA ENTIDAE NO BANCO DE DADOS PASSANDO O NOME DA ENIDADE 'name="users". '
 * '@Table' -> REPRESENTA UMA TABELA NO BANCO DE DADOS PASSANDO O NOME DA TABELA 'name="users". '
 * '@Getter' -> GERA GETTERS - lombok
 * '@Setter' -> GERA SETTERS - lombok
 * '@AllArgsConstructor' -> CRIA UM CONSTRUTOR QUE RECEBA TODOS OS PARAMETRS DA CLASSE - lombok 
 * '@EqualsAndHashCode' -> GERA A CHAVE PRIMÁRIA - lombok
 * 
 * '@Column(unique=true)' FAZ COM QUE O CPF 'DOCUMENT' SEJA UNICO, NÃO PODENDO HAVER OUTROS
 * 
 * '@Enumerated(EnumType.STRING)' SERVE PARA MOSTRAR QUE 'UserType' REPRESENTA MERCHANT OU COMMON.
 * */

package com.picpaysimplificado.domain.user;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//ANOTAÇÕES
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
@Table(name="users")
@Getter 
@Setter 
@EqualsAndHashCode(of="id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String fistName; //PRIMEIRO NOME
	private String lastName; //SOBRE NOME
	
	@Column(unique=true)
	private String document; //CPF
	
	@Column(unique=true)
	private String email; //EMAIL
	
	private String password; //SENHA
	private BigDecimal balance; //SALDO TOTAL
	
	@Enumerated(EnumType.STRING)
	private UserType userType; //TIPO DO USUÁRIO
}
