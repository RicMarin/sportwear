package it.contrader.dao;

import it.contrader.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findById(int id);

    Boolean existsById(int id);
}
