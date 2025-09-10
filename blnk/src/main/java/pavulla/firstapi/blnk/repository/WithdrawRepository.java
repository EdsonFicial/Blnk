package pavulla.firstapi.blnk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pavulla.firstapi.blnk.models.WithdrawEntity;

public interface WithdrawRepository extends JpaRepository<WithdrawEntity, Long> {
    // Additional query methods can be defined here if needed
    
}
