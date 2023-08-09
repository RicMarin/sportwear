package it.contrader.dao;

import it.contrader.model.Clothes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClothesRepository extends CrudRepository<Clothes, Long> {


    Clothes findById(int id);

    boolean existsById(int id);
}
