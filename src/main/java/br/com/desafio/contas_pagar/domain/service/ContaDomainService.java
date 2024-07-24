package br.com.desafio.contas_pagar.domain.service;

import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.domain.model.Situacao;
import br.com.desafio.contas_pagar.exception.NotFoundException;
import br.com.desafio.contas_pagar.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContaDomainService {

    private final ContaRepository contaRepository;

    public Conta getContaById(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Conta create(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta update(Long id, Conta conta) {
        if (!contaRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        conta.setId(id);
        return contaRepository.save(conta);
    }

    public void updateSituacao(Long id, Situacao situacao) {
        Conta conta = getContaById(id);
        if (conta == null) {
            throw new NotFoundException(id);
        } else {
            conta.setSituacao(situacao);
            contaRepository.save(conta);
        }
    }

    public List<Conta> getContasByDateAndDescription(LocalDate startDate, LocalDate endDate, String descricao) {
        return contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, descricao);
    }

    public BigDecimal getTotalPaidBetweenDates(LocalDate startDate, LocalDate endDate) {
        return contaRepository.findTotalPaidBetweenDates(startDate, endDate);
    }
}
