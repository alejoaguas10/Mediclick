package com.mediclick.util.report;

import com.mediclick.entities.Insumos;
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

public class InsumosExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Insumos> insumosList;

    public InsumosExcelExporter(List<Insumos> insumosList) {
        this.insumosList = insumosList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Insumos");
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
        cell.setCellValue("Id Insumos");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Fecha Entrada");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Registro");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("Codigo Insumo");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Nombre");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("Cantidad");
        cell.setCellStyle(style);
    }

    // Cells Settings
    private void datableWriter() {
        int rowNumber = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Insumos insumos : insumosList) {
            Row row = sheet.createRow(rowNumber++);

            Cell cell = row.createCell(0);
            cell.setCellValue(insumos.getIdinsumos());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(insumos.getFecha_entrada());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);


            cell = row.createCell(2);
            cell.setCellValue(insumos.getRegistro());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(insumos.getCodigo_insumo());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(insumos.getNombre());
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(insumos.getCantidad());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

        }
    }

    // Export settings
    public void export(HttpServletResponse response) throws IOException {
        headerWriter();
        datableWriter();

        ServletOutputStream outPutStream = response.getOutputStream();
        workbook.write(outPutStream);

        workbook.close();
        outPutStream.close();
    }

}
