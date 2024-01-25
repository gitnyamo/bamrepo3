package crud.bam.simplecrudoperationspringproject.repository;

import crud.bam.simplecrudoperationspringproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
