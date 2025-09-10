package pavulla.firstapi.blnk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pavulla.firstapi.blnk.models.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
