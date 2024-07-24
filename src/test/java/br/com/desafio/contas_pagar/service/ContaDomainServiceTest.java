package br.com.desafio.contas_pagar.service;

import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.domain.repository.ContaRepository;
import br.com.desafio.contas_pagar.domain.service.ContaDomainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.desafio.contas_pagar.domain.model.Situacao.PAGA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaDomainServiceTest {

    @InjectMocks
    ContaDomainService contaDomainService;

    @Mock
    ContaRepository contaRepository;

    @Test
    @DisplayName("GET Conta by Id")
    void testGetContaById() {
        Conta conta = new Conta();

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        Conta foundConta = contaDomainService.getContaById(1L);

        assertAll(
            () -> assertNotNull(foundConta),
            () -> verify(contaRepository, times(1)).findById(1L),
            () -> verifyNoMoreInteractions(contaRepository)
        );
    }

    @Test
    @DisplayName("Create conta")
    void testCreateConta() {
        Conta conta = new Conta();

        when(contaRepository.save(conta)).thenReturn(conta);

        Conta createdConta = contaDomainService.create(conta);

        assertAll(
            () -> assertNotNull(createdConta),
            () -> verify(contaRepository, times(1)).save(conta)
        );
    }

    @Test
    void testUpdateConta() {
        Conta conta = new Conta();

        when(contaRepository.existsById(1L)).thenReturn(true);
        when(contaRepository.save(conta)).thenReturn(conta);

        Conta updatedConta = contaDomainService.update(1L, conta);

        assertAll(
            () -> assertNotNull(updatedConta),
            () -> verify(contaRepository, times(1)).existsById(1L),
            () -> verify(contaRepository, times(1)).save(conta)
        );
    }

    @Test
    void testUpdateSituacao() {
        Conta conta = new Conta();

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        contaDomainService.updateSituacao(1L, PAGA);

        assertAll(
            () -> verify(contaRepository, times(1)).findById(1L),
            () -> verify(contaRepository, times(1)).save(conta)
        );
    }

    @Test
    void testGetContasByDateAndDescription() {
        Conta conta = new Conta();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, "descricao"))
                .thenReturn(Arrays.asList(conta));

        List<Conta> contas = contaDomainService.getContasByDateAndDescription(startDate, endDate, "descricao");

        assertAll(
            () -> assertEquals(1, contas.size()),
            () -> verify(contaRepository, times(1))
                    .findByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, "descricao")
        );
    }

    @Test
    void testGetTotalPaidBetweenDates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(contaRepository.findTotalPaidBetweenDates(startDate, endDate)).thenReturn(BigDecimal.TEN);

        BigDecimal totalPaid = contaDomainService.getTotalPaidBetweenDates(startDate, endDate);

        assertAll(
            () -> assertEquals(BigDecimal.TEN, totalPaid),
            () -> verify(contaRepository, times(1)).findTotalPaidBetweenDates(startDate, endDate)
        );
    }
}
