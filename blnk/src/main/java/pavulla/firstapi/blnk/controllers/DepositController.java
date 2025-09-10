package pavulla.firstapi.blnk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import pavulla.firstapi.blnk.Service.ServiceImpl.UserServiceImpl;
import pavulla.firstapi.blnk.dto.DepositDTO;
import pavulla.firstapi.blnk.models.DepositEntity;
import pavulla.firstapi.blnk.repository.DepositRepository;



 

@RestController
@RequestMapping("/blnk")
public class DepositController {
    
    @Autowired
    private UserServiceImpl userService ;

    @Autowired
    private DepositRepository depositRepository;
    
    @PostMapping("/deposits")
    public ResponseEntity<DepositEntity> DepositMoney(@RequestBody DepositDTO deposit) {
        
        DepositEntity deposited = userService.depositMoney(deposit);
        return ResponseEntity.status(HttpStatus.CREATED).body(deposited);
        // Logic to save the deposit
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
