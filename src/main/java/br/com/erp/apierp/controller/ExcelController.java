package br.com.erp.apierp.controller;

import br.com.erp.apierp.service.ExcelReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelReaderService service;

    @PostMapping("/upload")
    public ResponseEntity<List<List<String>>> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {

        String filePath = System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        var data = service.readerExcelFile(filePath);

        return ResponseEntity.ok(data);
    }
}
