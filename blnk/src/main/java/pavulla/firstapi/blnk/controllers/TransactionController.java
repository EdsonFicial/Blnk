package pavulla.firstapi.blnk.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import pavulla.firstapi.blnk.dto.CheckBalanceDTO;
import pavulla.firstapi.blnk.dto.DepositDTO;
import pavulla.firstapi.blnk.dto.WithdrawDTO;
import pavulla.firstapi.blnk.models.AccountEntity;
import pavulla.firstapi.blnk.models.DepositEntity;
import pavulla.firstapi.blnk.models.WithdrawEntity;
import pavulla.firstapi.blnk.Service.ServiceImpl.UserServiceImpl;



 

@RestController
@RequestMapping("/blnk")
public class TransactionController {
    
    @Autowired
    private UserServiceImpl userService ;

    
    
    
    
    @PostMapping("/deposits")
    public ResponseEntity DepositMoney(@RequestBody DepositDTO deposit) {

        HashMap<String, String> error = new HashMap<>();
        
        // Validação do accountId
        if (deposit.getAccount() == null || deposit.getAccount().isBlank()) {
            error.put("error", "400");
            error.put("message", "account is required");
            return ResponseEntity.badRequest().body(error);
        }

        // Validação do valor
        if (deposit.getAmount() == 0.0 || deposit.getAmount() < 100) {
            error.put("error", "400");
            error.put("message", "invalid amount (minimum is 100)");
            return ResponseEntity.badRequest().body(error);
        }

        // Executa o serviço
        try {
            DepositEntity deposited = userService.depositMoney(deposit);
            return ResponseEntity.status(HttpStatus.CREATED).body(deposited);

        } catch (EntityNotFoundException e) {
            error.put("error", "404");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (Exception e) {
            error.put("error", "500");
            error.put("message", "unexpected error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }



    @PostMapping("/withdraw")
    public ResponseEntity WithdrawMoney(@RequestBody WithdrawDTO withdraw) {

        HashMap<String, String> error = new HashMap<>();
        
        // Validação do accountId
        if (withdraw.getAccount() == null || withdraw.getAccount().isBlank()) {
            error.put("error", "400");
            error.put("message", "account is required");
            return ResponseEntity.badRequest().body(error);
        }

        // Validação do valor
        if (withdraw.getAmount() == 0.0 || withdraw.getAmount() < 100) {
            error.put("error", "400");
            error.put("message", "invalid amount (minimum is 100)");
            return ResponseEntity.badRequest().body(error);
        }

        // Executa o serviço
        try {
            WithdrawEntity w = userService.withdrawMoney(withdraw);
            return ResponseEntity.status(HttpStatus.CREATED).body(w);

        } catch (EntityNotFoundException e) {
            error.put("error", "404");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (Exception e) {
            error.put("error", "500");
            error.put("message", "unexpected error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity GetTransactions(@PathVariable("id") String id){
        HashMap<String, String> error = new HashMap<>();

        if (id == null || id.isEmpty()) {
            error.put("error", "400");
            error.put("message", "account is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // Executa o serviço
        try {
            var trx = userService.getUserTransactions(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(trx);

        } catch (EntityNotFoundException e) {
            error.put("error", "404");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (Exception e) {
            error.put("error", "500");
            error.put("message", "unexpected error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

    }

    @GetMapping("/checkbalance/{id}")
    public ResponseEntity<?> checkBalance(@PathVariable String id) {

        HashMap<String, String> error = new HashMap<>();
    
        if (id == null || id.isEmpty()) {
            
            error.put("error", "400");
            error.put("message", "Account ID is required");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }

        var dto = new CheckBalanceDTO();
        dto.setAccount(id);

        AccountEntity account = userService.checkBalance(dto);
        if (account == null) {
            
            error.put("error", "404");
            error.put("message", "Account not found");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        return ResponseEntity.ok(account);
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
