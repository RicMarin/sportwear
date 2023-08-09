package it.contrader.controller;

import it.contrader.dto.TransactionDTO;
import it.contrader.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(value = "/getAllTransactions")
    public List<TransactionDTO> getAll(){
        return transactionService.getAll();
    }

    @GetMapping(value = "/getTransaction/{id}")
    public TransactionDTO getById(@PathVariable int id){
        return transactionService.getById(id);
    }

    @GetMapping(value = "/getAllTransactionsByIdUser/{id}")
    public List<TransactionDTO> getByIdUser(@PathVariable int id){
        return transactionService.getByUserId(id);
    }

    @PostMapping(value = "/insert")
    public void insertTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.insertTransaction(transactionDTO);
    }

    @PostMapping(value = "/update")
    public void updateTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.updateTransaction(transactionDTO);
    }

    @DeleteMapping(value = "/delete")
    public void deleteTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.deleteTransaction(transactionDTO);
    }
}
