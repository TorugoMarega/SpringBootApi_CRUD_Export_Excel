package br.com.torugo.cadatro_pessoa_excel.service;

import br.com.torugo.cadatro_pessoa_excel.DTO.LoginDTO;
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
    public Boolean verifyLogin(LoginDTO loginDTO){
        Boolean logado=false;
        PersonModel personModel = this.repository.findByEmail(loginDTO.getEmail());
        Boolean checkPassword = personModel.getPassword().equals(loginDTO.getPassword());
        if(checkPassword){
            logado=true;
        }
        if(logado){
            System.out.println(personModel.getName()+": Usuário Logado!");
        }
        return logado;
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
