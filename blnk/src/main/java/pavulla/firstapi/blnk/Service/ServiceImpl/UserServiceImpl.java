package pavulla.firstapi.blnk.Service.ServiceImpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transaction;
import pavulla.firstapi.blnk.Service.UserService;
import pavulla.firstapi.blnk.dto.*;
import pavulla.firstapi.blnk.models.*;
import pavulla.firstapi.blnk.repository.*; 

@Service
public class UserServiceImpl implements UserService {

    

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DepositRepository depositRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private WithdrawRepository withdrawRepository;
    // private UserResponseDTO ur;
    // private UserEntity user;
    // private TransactionEntity transaction;
    

    // O Spring vai injetar o repositório aqui
    @Autowired
    public UserServiceImpl(UserRepository userRepository, DepositRepository depositRepository) {
        this.userRepository = userRepository;
        this.depositRepository = depositRepository;

        // ❌ NÃO faça chamadas ao repositório aqui
        // Ex: userRepository.findAll();  <-- Remover
    }
    
    @Override
    public UserResponseDTO CreateUser(CreateUserDTO dto){
        UserEntity user = new UserEntity();
       
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setbalance(0.0);
        UserEntity usr = userRepository.save(user);

        UserResponseDTO ur = new UserResponseDTO();

        ur = new UserResponseDTO(
            usr.getId(),
            usr.getName(),
            usr.getEmail(),
            usr.getbalance()
        );

        //System.out.println("User created successfully: " + user.toString());
        System.out.println("Use created Successful: " + ur.toString());
        return ur;
        
    }   
    
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        
        return users.stream()
        .map(user -> new UserResponseDTO(user.getId(),
            user.getName(),user.getEmail(),
            user.getbalance())).collect(Collectors.toList());
    }
    @Override
    public Transaction getUserTransactions(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepositEntity depositMoney(DepositDTO depositDTO) {
        List<UserEntity> users = userRepository.findAll();
        DepositEntity deposit = new DepositEntity();
    
            if(depositDTO.getUserId()!=null && !depositDTO.getUserId().equals("")){
                for(UserEntity user : users){
                    if(user.getId().equals(depositDTO.getUserId())) {
                        deposit.setAmount(depositDTO.getAmount());
                        deposit.setDepositedAt(LocalDateTime.now());
                        deposit.setUser(user);
                        user.setbalance(user.getbalance() + depositDTO.getAmount());
                        depositRepository.save(deposit);
                        System.out.println("" + user.getName() + " has deposited " + depositDTO.getAmount() + " at " + LocalDateTime.now());    
                        // Save the transaction Entity
                        TransactionEntity transaction = new TransactionEntity();
                        transaction.setTipo("deposit");
                        transaction.setValor(depositDTO.getAmount());
                        transaction.setData(depositDTO.getDepositedAt());
                        transaction.setUserId(depositDTO.getUserId());
                        transaction.setUserName(user.getName());
                        transactionRepository.save(transaction);
                        
                    }else {
                        System.out.println("User not found for ID: " + depositDTO.getUserId()); 
                    }
                }
            }else{
                System.out.println("User ID is null");
                return null;
            }
        return deposit;
    }
    
    @Override
    public WithdrawEntity withdrawMoney(WithdrawDTO withdrawDTO) {
        List<UserEntity> users = userRepository.findAll();
        WithdrawEntity withdraw = new WithdrawEntity();

            // Save the withdrawal entity
            if(withdrawDTO.getUserId() != null ) {
                for(UserEntity user : users) {
                    if(user.getId().equals(withdrawDTO.getUserId())) {

                        TransactionEntity transaction = new TransactionEntity();
                        transaction.setUserName(user.getName());
                        user.setbalance(user.getbalance() - withdrawDTO.getAmount());
                        userRepository.save(user);
                        withdraw.setAmount(withdrawDTO.getAmount());
                        System.out.println("" + user.getName() + " has withdrawn " + withdrawDTO.getAmount() + " at " + LocalDateTime.now());
                        withdraw.setUserId(withdrawDTO.getUserId());
                        withdraw.setTimestamp(LocalDateTime.now());
                        withdrawRepository.save(withdraw);
                        
                        // Save the transaction 
                        transaction.setTipo("withdraw");
                        transaction.setValor(withdrawDTO.getAmount());
                        transaction.setData(LocalDateTime.now());
                        transaction.setUserId(withdrawDTO.getUserId());
                    }else {
                        System.out.println("User not found for ID: " + withdrawDTO.getUserId());
                    }
                }   

            } else {
            System.out.println("User ID is null");
            }
        return null;
    }
    
    

    

    
}
