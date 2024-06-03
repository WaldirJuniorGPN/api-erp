package br.com.erp.apierp.service.impl;

import br.com.erp.apierp.service.ExcelReaderService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {

    @Override
    public List<List<String>> readerExcelFile(String filePath) throws IOException {

        List<List<String>> sheetData = new ArrayList<>();

        var workbook = getWorkbook(new File(filePath));

        // Obtém a primeira folha do arquivo
        Sheet sheet = workbook.getSheetAt(0);

        // Itera sobre todas as linhas da folha
        for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.add(cell.getDateCellValue().toString());
                            } else {
                                rowData.add(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case FORMULA:
                            rowData.add(cell.getCellFormula());
                            break;
                        default:
                            rowData.add("");
                    }
                }
                sheetData.add(rowData);
            }
        }

        workbook.close();

        return sheetData;
    }

    private Workbook getWorkbook(File file) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        String fileName = file.getName();
        Workbook workbook;
        if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(fis);
        } else {
            throw new IllegalArgumentException("O arquivo fornecido não é um arquivo Excel.");
        }
        fis.close();
        return workbook;
    }
}
