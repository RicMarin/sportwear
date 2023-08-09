package it.contrader.dao;

import it.contrader.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findAll();

    Transaction findById(int id);

    List<Transaction> findByIdUser(int id);

}
