package br.com.desafio.contas_pagar.domain.repository;

import br.com.desafio.contas_pagar.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByDataVencimentoBetweenAndDescricaoContaining(LocalDate startDate, LocalDate endDate, String descricao);

    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataPagamento BETWEEN :startDate AND :endDate")
    BigDecimal findTotalPaidBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
