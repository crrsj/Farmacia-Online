package br.com.popular.dto;


import java.util.List;

public class CompraRequest {
    private ClienteDTO cliente;
    private List<ItemCompra> itens;
    private PagamentoDTO pagamento;

    public CompraRequest() {
    }

    public CompraRequest(ClienteDTO cliente, List<ItemCompra> itens, PagamentoDTO pagamento) {
        this.cliente = cliente;
        this.itens = itens;
        this.pagamento = pagamento;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }
}
