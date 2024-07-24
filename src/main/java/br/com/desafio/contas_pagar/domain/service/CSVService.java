package br.com.desafio.contas_pagar.domain.service;

import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.domain.model.Situacao;
import br.com.desafio.contas_pagar.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CSVService {

    private final ContaRepository contaRepository;

    public void importCSV(MultipartFile file) {
        try {
            List<Conta> contas = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Conta conta = new Conta();
                conta.setDataVencimento(LocalDate.parse(data[0]));
                conta.setDataPagamento(LocalDate.parse(data[1]));
                conta.setValor(new BigDecimal(data[2]));
                conta.setDescricao(data[3]);
                conta.setSituacao(Situacao.valueOf(data[4]));
                contas.add(conta);
            }
            contaRepository.saveAll(contas);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar CSV: " + e.getMessage());
        }
    }
}
