package br.com.torugo.cadatro_pessoa_excel.service;

import br.com.torugo.cadatro_pessoa_excel.model.PersonModel;
import br.com.torugo.cadatro_pessoa_excel.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@ApplicationScope
@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    public PersonModel savePerson(PersonModel personModel){
        return this.repository.save(personModel);
    }
    public Boolean existsByEmail(PersonModel personModel){
        return this.repository.existsByEmail(personModel.getEmail());
    }

    public List<PersonModel> listAllPerson() {
        return this.repository.findAll();
    }

    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    public PersonModel patchPerson(PersonModel patch_personModel) {
        return this.repository.save(patch_personModel);
    }
}
