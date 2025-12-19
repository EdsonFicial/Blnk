package pavulla.firstapi.blnk.Service;
import pavulla.firstapi.blnk.dto.CheckBalanceDTO;
import pavulla.firstapi.blnk.dto.CreateUserDTO;
import pavulla.firstapi.blnk.dto.DepositDTO;
import pavulla.firstapi.blnk.dto.UserResponseDTO;
import pavulla.firstapi.blnk.dto.WithdrawDTO;
import pavulla.firstapi.blnk.models.AccountEntity;
import pavulla.firstapi.blnk.models.DepositEntity;
import pavulla.firstapi.blnk.models.TransactionEntity;
import pavulla.firstapi.blnk.models.WithdrawEntity;

import java.util.List;



public interface UserService {
    
    UserResponseDTO CreateUser(CreateUserDTO createUserDTO);
    List<UserResponseDTO> getAllUsers();
    DepositEntity depositMoney(DepositDTO depositDTO);
    WithdrawEntity withdrawMoney(WithdrawDTO withdrawDTO);
    List<TransactionEntity> getUserTransactions(String userId);
    AccountEntity checkBalance(CheckBalanceDTO checkBalanceDTO);
}
