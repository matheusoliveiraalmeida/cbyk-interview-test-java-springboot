package br.com.desafio.contas_pagar.controller;

import br.com.desafio.contas_pagar.application.dto.ContaDTO;
import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.application.ContaController;
import br.com.desafio.contas_pagar.domain.service.ContaApplicationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static br.com.desafio.contas_pagar.domain.model.Situacao.PAGA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaControllerTest {

    @InjectMocks
    ContaController contaController;

    @Mock
    ContaApplicationService contaApplicationService;

    @Test
    void testCreateConta() {
        Conta conta = new Conta();

        when(contaApplicationService.create(conta.toDTO())).thenReturn(conta.toDTO());

        ResponseEntity<ContaDTO> response = contaController.create(conta.toDTO());

        assertAll(
            () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
            () -> assertNotNull(response.getBody()),
            () -> verify(contaApplicationService, times(1)).create(conta.toDTO()),
            () -> verifyNoMoreInteractions(contaApplicationService)
        );
    }

    @Test
    @DisplayName("Update conta")
    void testUpdateConta() {
        Conta conta = new Conta();

        when(contaApplicationService.update(1L, conta.toDTO())).thenReturn(conta.toDTO());

        ResponseEntity<ContaDTO> response = contaController.update(1L, conta.toDTO());

        assertAll(
            () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
            () -> assertNotNull(response.getBody()),
            () -> verify(contaApplicationService, times(1)).update(1L, conta.toDTO()),
            () -> verifyNoMoreInteractions(contaApplicationService)
        );
    }

    @Test
    void testUpdateSituacao() {
        ResponseEntity<Void> response = contaController.updateSituacao(1L, PAGA);

        assertAll(
            () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
            () -> verify(contaApplicationService, times(1)).updateSituacao(1L, PAGA),
            () -> verifyNoMoreInteractions(contaApplicationService)
        );
    }

    @Test
    void testGetContasByDateAndDescription() {
        ContaDTO contaDTO = new ContaDTO(new Conta());
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(contaApplicationService.getContasByDateAndDescription(startDate, endDate, "descricao"))
                .thenReturn(Arrays.asList(contaDTO));

        ResponseEntity<List<ContaDTO>> response = contaController.getContasByDateAndDescription(startDate, endDate, "descricao");

        assertAll(
            () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
            () -> assertEquals(1, response.getBody().size()),
            () -> verify(contaApplicationService, times(1))
                    .getContasByDateAndDescription(startDate, endDate, "descricao"),
            () -> verifyNoMoreInteractions(contaApplicationService)
        );
    }

    @Test
    void testGetTotalPaidBetweenDates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(contaApplicationService.getTotalPaidBetweenDates(startDate, endDate)).thenReturn(BigDecimal.TEN);

        ResponseEntity<BigDecimal> response = contaController.getTotalPaidBetweenDates(startDate, endDate);

        assertAll(
            () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
            () -> assertEquals(BigDecimal.TEN, response.getBody()),
            () -> verify(contaApplicationService, times(1)).getTotalPaidBetweenDates(startDate, endDate)
        );
    }
}
