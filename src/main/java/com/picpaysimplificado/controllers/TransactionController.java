/* ANOT
 * 
 * @RequestMapping("/transactions") - TODAS AS REQUISIÇÕES QUE FOREM MANDADAS PARA '/transactions' A CLASSE ABAIXO IRA TEMAS CONTA  
 *   
 * */

package com.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
		Transaction newTransaction = this.transactionService.createTransaction(transaction);
		return new ResponseEntity<>(newTransaction, HttpStatus.OK);	
	}

}
