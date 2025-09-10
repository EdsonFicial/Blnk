package pavulla.firstapi.blnk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pavulla.firstapi.blnk.models.DepositEntity;

@Repository
public interface DepositRepository extends JpaRepository<DepositEntity, String> {
}
