package br.com.desafio.contas_pagar.domain.service;

import br.com.desafio.contas_pagar.application.dto.ContaDTO;
import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.domain.model.Situacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContaApplicationService {

    private final ContaDomainService contaDomainService;

    public ContaDTO getContaById(Long id) {
        return new ContaDTO(contaDomainService.getContaById(id));
    }

    public ContaDTO create(ContaDTO conta) {
        return new ContaDTO(contaDomainService.create(new Conta(conta)));
    }

    public ContaDTO update(Long id, ContaDTO conta) {
        return new ContaDTO(contaDomainService.update(id, new Conta(conta)));
    }

    public void updateSituacao(Long id, Situacao situacao) {
        contaDomainService.updateSituacao(id, situacao);
    }

    public List<ContaDTO> getContasByDateAndDescription(LocalDate startDate, LocalDate endDate, String descricao) {
        log.info(String.format("[ContaApplicationService] - getContasByDateAndDescription %s", descricao));

        List<Conta> contas = contaDomainService.getContasByDateAndDescription(startDate, endDate, descricao);

        return contas
                .stream()
                .map(Conta::toDTO)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalPaidBetweenDates(LocalDate startDate, LocalDate endDate) {
        return contaDomainService.getTotalPaidBetweenDates(startDate, endDate);
    }
}
