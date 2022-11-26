package br.com.torugo.cadatro_pessoa_excel.controller;

import br.com.torugo.cadatro_pessoa_excel.response.ConnectionResponse;
import br.com.torugo.cadatro_pessoa_excel.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    private static final Logger logger = LogManager.getLogger("TestConnection");

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity testConnection(){
        try{
            ConnectionResponse res = new ConnectionResponse("Conexão Estabelecida!" ,"/test");
            logger.info(res.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch (Exception ex){
            Response response = new Response("Servidor fora do Ar", "/test");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
