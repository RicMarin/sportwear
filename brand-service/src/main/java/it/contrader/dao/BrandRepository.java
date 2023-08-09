package it.contrader.dao;

import it.contrader.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BrandRepository extends CrudRepository<Brand, Long> {

    List<Brand> findAll();

    Brand findById(int id);

    boolean existsById(int id);
}
