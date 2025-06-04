package br.com.popular.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PagamentoDTO {

    public enum TipoPagamento { CARTAO, PIX, BOLETO }

    @NotNull(message = "Tipo de pagamento é obrigatório")
    private TipoPagamento tipo;

    // Campos condicionais (usados apenas para cartão)
    @Pattern(regexp = "\\d{16}|\\d{4}-\\d{4}-\\d{4}-\\d{4}",
            message = "Número do cartão inválido (use 16 dígitos ou formato XXXX-XXXX-XXXX-XXXX)")
    private String numeroCartao;

    private String titularCartao; // Opcional se tipo=CARTAO
    private String cpfTitular;   // Opcional se tipo=CARTAO

    // Getters e Setters
    public TipoPagamento getTipo() { return tipo; }
    public void setTipo(TipoPagamento tipo) { this.tipo = tipo; }
    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }
    public String getTitularCartao() { return titularCartao; }
    public void setTitularCartao(String titularCartao) { this.titularCartao = titularCartao; }
    public String getCpfTitular() { return cpfTitular; }
    public void setCpfTitular(String cpfTitular) { this.cpfTitular = cpfTitular; }
}
