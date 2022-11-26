package br.com.torugo.cadatro_pessoa_excel.arquivos;

import br.com.torugo.cadatro_pessoa_excel.model.PersonModel;
import br.com.torugo.cadatro_pessoa_excel.service.PersonService;
import br.com.torugo.cadatro_pessoa_excel.exporter.UserExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/arquivo")
public class GerarArquivoController {
    @Autowired
    private PersonService service;

    @GetMapping("/export/excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=person_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<PersonModel> listOfStudents = this.service.listAllPerson();
        UserExcelExporter generator = new UserExcelExporter(listOfStudents);
        generator.generateExcelFile(response);
    }
}
