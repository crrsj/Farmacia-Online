package br.com.popular.excessoes;

public class EstoqueInsuficienteException extends RuntimeException{
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}
