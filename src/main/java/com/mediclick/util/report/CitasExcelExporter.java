package com.mediclick.util.report;

import com.mediclick.entities.Citas;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CitasExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Citas> orderList;
    public CitasExcelExporter(List<Citas> citasList) {
        this.orderList = citasList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Citas");
    }

    // Header settings
    private void headerWriter() {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("Id Citas");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Nombre Paciente");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Apellido Paciente");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("Hora");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Medico");
        cell.setCellStyle(style);
    }

    // Cells settings
    private void datatableWriter() {
        int rowNumber = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Citas citas : orderList) {
            Row row = sheet.createRow(rowNumber ++);

            Cell cell = row.createCell(0);
            cell.setCellValue(citas.getIdcitas());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(citas.getFecha().toString());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(citas.getNombre_paciente());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(citas.getApellido_paciente());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(citas.getHora());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(citas.getMedico());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);
        }
    }

    // Export settings
    public void export(HttpServletResponse response) throws IOException {
        headerWriter();
        datatableWriter();

        ServletOutputStream outPutStream = response.getOutputStream();
        workbook.write(outPutStream);

        workbook.close();
        outPutStream.close();
    }
}
