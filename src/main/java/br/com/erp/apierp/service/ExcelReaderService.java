package br.com.erp.apierp.service;

import java.io.IOException;
import java.util.List;

public interface ExcelReaderService {

    public List<List<String>> readerExcelFile(String filePath) throws IOException;
}
