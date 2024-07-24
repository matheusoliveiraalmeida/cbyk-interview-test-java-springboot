package br.com.desafio.contas_pagar.application;

import br.com.desafio.contas_pagar.domain.service.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contas")
public class CSVController {

    private final CSVService csvService;

    @PostMapping("/import")
    public ResponseEntity<Void> importCSV(@RequestParam("file") MultipartFile file) {
        csvService.importCSV(file);
        return new ResponseEntity<>(OK);
    }
}
