package br.com.desafio.contas_pagar.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Conta não encontrada com o id: " + id);
    }

}
