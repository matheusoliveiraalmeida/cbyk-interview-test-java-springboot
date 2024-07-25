package br.com.desafio.contas_pagar.application;

import br.com.desafio.contas_pagar.application.dto.ContaDTO;
import br.com.desafio.contas_pagar.domain.model.Situacao;
import br.com.desafio.contas_pagar.domain.service.ContaApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
@Slf4j
public class ContaController {

    private final ContaApplicationService contaApplicationService;

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> getContaById(@PathVariable Long id) {
        log.info("[GET] /api/contas {} ", id);
        return new ResponseEntity<>(contaApplicationService.getContaById(id), OK);
    }

    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestBody ContaDTO conta) {
        log.info("[POST] /api/contas {} ", conta);
        return new ResponseEntity<>(contaApplicationService.create(conta), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO conta) {
        return ResponseEntity.ok(contaApplicationService.update(id, conta));
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<Void> updateSituacao(@PathVariable Long id, @RequestParam Situacao situacao) {
        contaApplicationService.updateSituacao(id, situacao);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ContaDTO>> getContasByDateAndDescription(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam String descricao) {
        return new ResponseEntity<>(contaApplicationService.getContasByDateAndDescription(startDate, endDate, descricao), OK);
    }

    @GetMapping("/total-paid")
    public ResponseEntity<BigDecimal> getTotalPaidBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(contaApplicationService.getTotalPaidBetweenDates(startDate, endDate), OK);
    }
}
