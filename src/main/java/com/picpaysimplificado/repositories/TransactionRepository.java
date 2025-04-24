/* ANOT
 * INTERFACE DE REPOSITÓRIO USANDO STRING DATA JPA
 * 
 * 'TransactionRepository'
 *    É UMA INTERFACE QUE SERVE PARA ACESSAR E MANIPULAR DADOS DE ENTIDADE 'Transaction' NO BANCO
 * 
 * 'extends JpaRepository<Transaction, Long>' 
 *    HERDA OS METODOS PRONTOS DO SPRING DATA JPA PARA OPERAÇÕES DE DELETAR, SALVAR, BUSCAR, ETC
 * 
 * */

package com.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.picpaysimplificado.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}