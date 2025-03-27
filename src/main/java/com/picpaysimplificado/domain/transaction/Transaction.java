/* ANOT COD
 * 
 * '@ManyToOne' (RELAÇÃO DE MUITOS PRA UM) - DO (JPA) SERVE PARA MAPEAR UM RELACIONAMENTO DE MUITOS-PARA-UM ENTRE ENTIDADES.
 *     UM USUARIO PODE TER VARIAS TRANSAÇÕES, NESSE CONTEXTO
 *     MAS UMA TRANSAÇÃO PODE TER APENAS UM 'SENDER'(QUEM ENVIA) E UM 'RECEIVER'(QUEM RECEBE)
 *     
 * '@JoinColumn(name="receiver_id")' - DO (JPA) ESPECIFICA A COLUNA QUE SERA USADA COMO CHAVE ESTRANGEIRA EM UM RELACIONAMENTO DE ENTIDADES
 * 
 * */

package com.picpaysimplificado.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpaysimplificado.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal amount; //QUANTIA DA TRANSAÇÃO
	
	@ManyToOne 
	@JoinColumn(name="sender_id")
	private User sender; //QUEM ENVIA 
	
	@ManyToOne 
	@JoinColumn(name="receiver_id")
	private User receiver; // QUEM RECEBE (NÃO PODE ENVIAR)
	private LocalDateTime timestamp;
}
