package pavulla.firstapi.blnk.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transaction;
import pavulla.firstapi.blnk.dto.DepositDTO;
import pavulla.firstapi.blnk.models.DepositEntity;
import pavulla.firstapi.blnk.models.TransactionEntity;
import pavulla.firstapi.blnk.repository.DepositRepository;
import pavulla.firstapi.blnk.repository.TransactionRepository;
import pavulla.firstapi.blnk.service.ServiceImpl.UserServiceImpl;



 

@RestController
@RequestMapping("/blnk")
public class TransactionController {
    
    @Autowired
    private UserServiceImpl userService ;

    @Autowired
    private DepositRepository depositRepository;
    
    @PostMapping("/deposits")
    public ResponseEntity DepositMoney(@RequestBody DepositDTO deposit) {

        HashMap<String, String> error = new HashMap<>();
        
        if (deposit.getUserId() == null) {
            error.put("error", "400");
            error.put("message", "user id is required");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

            if (deposit.getAmount() < 100) {
            error.put("error", "400");
            error.put("message", "invalid amount");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        
        DepositEntity deposited = userService.depositMoney(deposit);
        if (deposited == null) {
            error.put("error", "500");
            error.put("message", "unexpected error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(deposited);
        // Logic to save the deposit
    }

    @GetMapping("/transactions/{userid}")
    public TransactionEntity GetTransactions(@PathVariable String id){


        return null;
    }

    @GetMapping("/deposits/{id}")
    public DepositEntity getDepositById(@PathVariable String id) {

        return depositRepository.findById(id).get();
                
        // List<DepositEntity> deposits = depositRepository.findAll();
        // for (DepositEntity deposit : deposits) {
        //     if (deposit.getId().equals(id)) {
        //         return deposit;
        //     }
        // }
        // return null;
        
    }

    
    // @GetMapping("/deposits")
    // public List<DepositEntity> getAllDeposits() {
    //     // Logic to retrieve all deposits
    // }
    
    // @PutMapping("/deposits/{id}")
    // public ResponseEntity<DepositEntity> updateDeposit(@PathVariable String id, @RequestBody DepositEntity deposit) {
    //     // Logic to update a deposit
    // }
    
    // @DeleteMapping("/deposits/{id}")
    // public ResponseEntity<Void> deleteDeposit(@PathVariable String id) {
    //     // Logic to delete a deposit
    // }
}
