package br.com.torugo.cadatro_pessoa_excel.controller;

import br.com.torugo.cadatro_pessoa_excel.DTO.PersonDTO;
import br.com.torugo.cadatro_pessoa_excel.model.PersonModel;
import br.com.torugo.cadatro_pessoa_excel.exception.GenericException;
import br.com.torugo.cadatro_pessoa_excel.response.Response;
import br.com.torugo.cadatro_pessoa_excel.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @Transactional
    @PostMapping(produces="application/json", consumes = "application/json")
    public ResponseEntity createPerson(@RequestBody PersonModel personModel){
        try{
            if(this.service.existsByEmail(personModel)){
                PersonDTO personDTO = new PersonDTO(personModel.getName(), personModel.getEmail());
                GenericException ex = new GenericException("Usuário já Cadastrado com este email!", personDTO, LocalDateTime.now());
                throw ex;
            }else {
                this.service.savePerson(personModel);
                Response response = new Response("Usuário Criado com Sucesso!", personModel,"/person");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }catch (GenericException ex){

            Response response = new Response(ex.getMessage(), ex.getObject(), "/person");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PatchMapping
    public ResponseEntity patchPerson(@RequestBody PersonModel personModel){
        try{
            if(this.service.existsById(personModel.getId())){
                this.service.patchPerson(personModel);
                Response response = new Response("Usuário atualizado com sucesso!", personModel, "/person");
                return new ResponseEntity(response, HttpStatus.OK);
            }else {
                GenericException ex = new GenericException("Usuário Não Existe com este ID: "+ personModel.getId(), personModel, LocalDateTime.now());
                throw ex;
            }

        }catch (GenericException ex){
            Response response = new Response(ex.getMessage(), ex.getObject(),"/person");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces="application/json")
    public List<PersonModel> listAllPerson(){
        return this.service.listAllPerson();
    }


}
