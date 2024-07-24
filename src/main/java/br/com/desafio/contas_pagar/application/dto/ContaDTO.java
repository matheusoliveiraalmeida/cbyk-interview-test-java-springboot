package br.com.desafio.contas_pagar.application.dto;

import br.com.desafio.contas_pagar.domain.model.Conta;
import br.com.desafio.contas_pagar.domain.model.Situacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContaDTO(
    Long id,
    @NotNull LocalDate dataVencimento,
    @NotNull LocalDate dataPagamento,
    @NotNull BigDecimal valor,
    @NotNull @NotEmpty String descricao,
    @NotNull Situacao situacao
) {

    public ContaDTO(Conta conta) {
        this(
            conta.getId(),
            conta.getDataVencimento(),
            conta.getDataPagamento(),
            conta.getValor(),
            conta.getDescricao(),
            conta.getSituacao()
        );
    }

}