/* ANOT COD
 * 
 * 'record' - SERVE PARA REPRESENTAR DE FORMA CONCISA UM TIPO DE DADOS IMUTÁVEL QUE ARMAZENA UM CONJUNTO DE VALORES.
 * 
 * */

package com.picpaysimplificado.dto;

import java.math.BigDecimal;

public record TransactionDTO(
		BigDecimal value, 
		String senderDocument, 
		String receiverDocument) {}
