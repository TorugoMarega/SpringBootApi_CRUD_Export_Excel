package br.com.torugo.cadatro_pessoa_excel.repository;

import br.com.torugo.cadatro_pessoa_excel.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {
    public boolean existsByEmail(String email);
   public PersonModel findByEmail(String email);
}
