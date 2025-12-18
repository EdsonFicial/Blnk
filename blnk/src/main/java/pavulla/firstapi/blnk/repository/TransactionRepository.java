package pavulla.firstapi.blnk.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import pavulla.firstapi.blnk.models.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    // Additional query methods can be defined here if needed
    // List all transactions by user ID

    

}
