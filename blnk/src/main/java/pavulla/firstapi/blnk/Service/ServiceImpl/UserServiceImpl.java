package pavulla.firstapi.blnk.Service.ServiceImpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transaction;
import lombok.val;
import pavulla.firstapi.blnk.dto.*;
import pavulla.firstapi.blnk.models.*;
import pavulla.firstapi.blnk.repository.*;
import pavulla.firstapi.blnk.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DepositRepository depositRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;
    

    // O Spring will inject the UserRepository here
    public UserServiceImpl(UserRepository userRepository, DepositRepository depositRepository,TransactionRepository transactionRepository,AccountRepository accRepository) {
        this.userRepository = userRepository;
        this.depositRepository = depositRepository;
        this.transactionRepository = transactionRepository;
        this.accRepository = accRepository;
    }
    
    @Override
    public UserResponseDTO CreateUser(CreateUserDTO dto){
        
        // 1️⃣ Create and save User FIRST (no accountId yet)
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user = userRepository.save(user); // 🔑 now user.getId() exists

        // 2️⃣ Create and save Account using userId
        AccountEntity acc = new AccountEntity();
        acc.setUserid(user.getId());       // 🔑 REQUIRED
        acc.setUser_name(user.getName());
        acc.setBalance(0.0);

        acc = accRepository.save(acc);     // 🔑 now acc.getId() exists

        // 3️⃣ Update User with accountId
        user.setAccountId(acc.getId());
        userRepository.save(user);         // 🔑 update

        UserResponseDTO ur = new UserResponseDTO();
        ur = new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            acc.getId(),
            acc.getBalance()
        );

        //System.out.println("User created successfully: " + user.toString());
        System.out.println("Use created Successful: " + ur.toString());
        return ur;
        
    }   
    
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        AccountEntity acc = new AccountEntity();  
        return users.stream()
        .map(user -> new UserResponseDTO(user.getId(),
            user.getName(),
            user.getEmail(),
            acc.getId(),
            acc.getBalance()
            )).collect(Collectors.toList()
        );
    }

    

    @Override
    public DepositEntity depositMoney(DepositDTO depositDTO) {
        
        //Busca a Conta
        AccountEntity account = accRepository.findById(depositDTO.getAccount())
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Account not found for id: " + depositDTO.getAccount()
            )
        );
        //Busca o Usuário dono da conta
        UserEntity user = userRepository.findById(account.getUserid())
        .orElseThrow(() ->
            new EntityNotFoundException(
                "User not found for id: " + account.getUserid()
            )
        );

        //Atualiza o saldo da conta
        account.setBalance(account.getBalance() + depositDTO.getAmount());
        accRepository.save(account);

        //Criar o depósito
        DepositEntity deposit = new DepositEntity();
        deposit.setAmount(depositDTO.getAmount());
        deposit.setDepositedAt(LocalDateTime.now());
        deposit.setUser(user);
        deposit.setUser_name(user.getName());
        deposit.setAccount(account);

        depositRepository.save(deposit);

        // Criar a transação
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTipo("deposit");
        transaction.setValor(depositDTO.getAmount());
        transaction.setData(LocalDateTime.now());
        transaction.setUserId(user.getId());
        transaction.setUserName(user.getName());

        transactionRepository.save(transaction);
        return deposit;
        
    }

    
   @Override
    public WithdrawEntity withdrawMoney(WithdrawDTO withdrawDTO) {
            
        //Busca a Conta
        AccountEntity account = accRepository.findById(withdrawDTO.getAccount())
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Account not found for id: " + withdrawDTO.getAccount()
            )
        );

        //Busca o Usuário dono da conta
        UserEntity user = userRepository.findById(account.getUserid())
        .orElseThrow(() ->
            new EntityNotFoundException(
                "User not found for id: " + account.getUserid()
            )
        );

        account.setBalance(account.getBalance() - withdrawDTO.getAmount());
        accRepository.save(account);

        WithdrawEntity withdraw = new WithdrawEntity();
        withdraw.setAmount(withdrawDTO.getAmount());
        withdraw.setUserId(user.getId());
        withdraw.setTimestamp(LocalDateTime.now());
        withdrawRepository.save(withdraw);

        // Criar a transação
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTipo("withdraw");
        transaction.setValor(withdrawDTO.getAmount());
        transaction.setData(LocalDateTime.now());
        transaction.setUserId(user.getId());
        transaction.setUserName(user.getName());
        transactionRepository.save(transaction);
        return withdraw;
    }


    @Override
    public AccountEntity checkBalance(CheckBalanceDTO checkBalanceDTO) {
        AccountEntity account = accRepository.findById(checkBalanceDTO.getAccount())
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Account not found for id: " + checkBalanceDTO.getAccount()
            )
        );
        return account;
    }

    @Override
    public List<TransactionEntity> getUserTransactions(String userId) {
        List<TransactionEntity> txs = transactionRepository.findAll()
        .stream()
        .filter(t -> t.getUserId().equals(userId))
        .toList();
        return txs;
    }
}
