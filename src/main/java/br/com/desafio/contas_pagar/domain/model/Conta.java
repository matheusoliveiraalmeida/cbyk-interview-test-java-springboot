package br.com.desafio.contas_pagar.domain.model;

import br.com.desafio.contas_pagar.application.dto.ContaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Column
    private BigDecimal valor;
    
    @Column
    private String descricao;
    
    @Column
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public Conta(ContaDTO contaDTO) {
        this.id = contaDTO.id();
        this.dataVencimento = contaDTO.dataVencimento();
        this.dataPagamento = contaDTO.dataPagamento();
        this.valor = contaDTO.valor();
        this.descricao = contaDTO.descricao();
        this.situacao = contaDTO.situacao();
    }

    public ContaDTO toDTO() {
        return ContaDTO.builder()
                .id(id)
                .dataVencimento(dataVencimento)
                .dataPagamento(dataPagamento)
                .valor(valor)
                .descricao(descricao)
                .situacao(situacao)
                .build();
    }

}
