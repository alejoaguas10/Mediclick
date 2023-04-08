package com.mediclick.util.report;

import com.mediclick.entities.Historiales;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HistorialesExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Historiales> historialesList;
    public HistorialesExcelExporter(List<Historiales> historialesList) {
        this.historialesList = historialesList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Historiales");
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
        cell.setCellValue("Id Historiales");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Numero Documento");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Nombre Paciente");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("Apellido Paciente");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Edad");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("Sexo");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("Motivo Consulta");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("Presion Arterial");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("Peso");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("Talla");
        cell.setCellStyle(style);
    }

    // Configuracion de celdas
    private void datatableWriter() {
        int rowNumber = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Historiales historiales : historialesList) {
            Row row = sheet.createRow(rowNumber ++);

            Cell cell = row.createCell(0);
            cell.setCellValue(historiales.getIdhistoriales());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(historiales.getNum_documento());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(historiales.getNombre_pac());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(historiales.getApellido_pac());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(historiales.getEdad());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(historiales.getSexo());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(historiales.getMotivo_consulta());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(7);
            cell.setCellValue(historiales.getPresion_arterial());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(8);
            cell.setCellValue(historiales.getPeso());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(9);
            cell.setCellValue(historiales.getTalla());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);
        }
    }

    // Configuracion de exportacion
    public void export(HttpServletResponse response) throws IOException {
        headerWriter();
        datatableWriter();

        ServletOutputStream outPutStream = response.getOutputStream();
        workbook.write(outPutStream);

        workbook.close();
        outPutStream.close();
    }
}
