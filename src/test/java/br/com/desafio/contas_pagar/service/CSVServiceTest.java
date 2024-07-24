package br.com.desafio.contas_pagar.service;

import br.com.desafio.contas_pagar.domain.service.CSVService;
import br.com.desafio.contas_pagar.domain.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CSVServiceTest {

    @InjectMocks
    CSVService csvService;

    @Mock
    ContaRepository contaRepository;

    @Test
    void testImportCSV() {
        String content = "2023-01-01,2023-01-02,100.00,descricao,PAGA\n";
        MultipartFile file = new MockMultipartFile("file", content.getBytes());

        csvService.importCSV(file);

        verify(contaRepository, times(1)).saveAll(any(List.class));
    }
}
