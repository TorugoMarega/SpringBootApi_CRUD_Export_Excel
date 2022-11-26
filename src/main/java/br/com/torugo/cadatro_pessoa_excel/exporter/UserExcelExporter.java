package br.com.torugo.cadatro_pessoa_excel.exporter;

import br.com.torugo.cadatro_pessoa_excel.model.PersonModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class UserExcelExporter {
    private List<PersonModel> personModelList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public UserExcelExporter(List<PersonModel> personModelList) {
        this.personModelList = personModelList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Person");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Created At", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        }
        else if (valueOfCell instanceof LocalDateTime) {
            cell.setCellValue((String) valueOfCell);
        }
        else if(valueOfCell instanceof LocalDate){
            cell.setCellValue((LocalDate) valueOfCell);
        }
        else if(valueOfCell instanceof Calendar){
            cell.setCellValue((Calendar) valueOfCell);
        }
        else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (PersonModel record : personModelList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getName(), style);
            createCell(row, columnCount++, record.getEmail(), style);
            createCell(row, columnCount++,
                    record.getCreated_at().getDayOfMonth()+"/"+
                            record.getCreated_at().getMonthValue()+"/"+
                            record.getCreated_at().getYear()+" "+
                            record.getCreated_at().getHour()+"h "+
                            record.getCreated_at().getMinute()+"m "+
                            record.getCreated_at().getSecond()+"s"
                    , style);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}