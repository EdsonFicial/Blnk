package pavulla.firstapi.blnk.Service;
import pavulla.firstapi.blnk.dto.CheckBalanceDTO;
import pavulla.firstapi.blnk.dto.CreateUserDTO;
import pavulla.firstapi.blnk.dto.DepositDTO;
import pavulla.firstapi.blnk.dto.UserResponseDTO;
import pavulla.firstapi.blnk.dto.WithdrawDTO;
import pavulla.firstapi.blnk.models.AccountEntity;
import pavulla.firstapi.blnk.models.DepositEntity;
import pavulla.firstapi.blnk.models.WithdrawEntity;

import java.util.List;

import jakarta.transaction.Transaction;

public interface UserService {
    
    UserResponseDTO CreateUser(CreateUserDTO createUserDTO);
    List<UserResponseDTO> getAllUsers();
    DepositEntity depositMoney(DepositDTO depositDTO);
    WithdrawEntity withdrawMoney(WithdrawDTO withdrawDTO);
    Transaction getUserTransactions(String userId);
    AccountEntity checkBalance(CheckBalanceDTO checkBalanceDTO);
}
