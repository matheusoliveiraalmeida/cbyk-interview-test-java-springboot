package br.com.desafio.contas_pagar.service;

import br.com.desafio.contas_pagar.application.dto.ContaDTO;
import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.domain.service.ContaApplicationService;
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

import static br.com.desafio.contas_pagar.domain.model.Situacao.PAGA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaApplicationServiceTest {

    @InjectMocks
    ContaApplicationService contaApplicationService;

    @Mock
    ContaDomainService contaDomainService;

    @Test
    @DisplayName("Ao chamar o GET Conta by Id")
    void testGetContaById() {

        when(contaDomainService.getContaById(1L)).thenReturn(new Conta());

        ContaDTO foundConta = contaApplicationService.getContaById(1L);

        assertAll(
            () -> assertNotNull(foundConta),
            () -> verify(contaDomainService, times(1)).getContaById(1L),
            () -> verifyNoMoreInteractions(contaDomainService)
        );
    }

    @Test
    void testCreateConta() {
        Conta conta = new Conta();

        when(contaDomainService.create(conta)).thenReturn(conta);

        ContaDTO createdConta = contaApplicationService.create(conta.toDTO());

        assertAll(
            () -> assertNotNull(createdConta),
            () -> verify(contaDomainService, times(1)).create(conta),
            () -> verifyNoMoreInteractions(contaDomainService)
        );
    }

    @Test
    void testUpdateConta() {
        Conta conta = new Conta();

        when(contaDomainService.update(1L, conta)).thenReturn(conta);

        ContaDTO updatedConta = contaApplicationService.update(1L, conta.toDTO());

        assertAll(
            () -> assertNotNull(updatedConta),
            () -> verify(contaDomainService, times(1)).update(1L, conta)
        );
    }

    @Test
    void testUpdateSituacao() {
        contaApplicationService.updateSituacao(1L, PAGA);

        verify(contaDomainService, times(1)).updateSituacao(1L, PAGA);
    }

    @Test
    void testGetContasByDateAndDescription() {
        Conta conta = new Conta();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(contaDomainService.getContasByDateAndDescription(startDate, endDate, "descricao"))
                .thenReturn(Arrays.asList(conta));

        List<ContaDTO> contas = contaApplicationService.getContasByDateAndDescription(startDate, endDate, "descricao");

        assertAll(
            () -> assertEquals(1, contas.size()),
            () -> verify(contaDomainService, times(1))
                    .getContasByDateAndDescription(startDate, endDate, "descricao")
        );
    }

    @Test
    void testGetTotalPaidBetweenDates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(contaDomainService.getTotalPaidBetweenDates(startDate, endDate)).thenReturn(BigDecimal.TEN);

        BigDecimal totalPaid = contaApplicationService.getTotalPaidBetweenDates(startDate, endDate);

        assertAll(
            () -> assertEquals(BigDecimal.TEN, totalPaid),
            () -> verify(contaDomainService, times(1)).getTotalPaidBetweenDates(startDate, endDate)
        );
    }
}
