package br.com.popular.servico;

import br.com.popular.dto.CompraRequest;
import br.com.popular.dto.ItemCompra;
import br.com.popular.excessoes.EstoqueInsuficienteException;
import br.com.popular.excessoes.ProdutoNaoEncontradoException;
import br.com.popular.repositorio.ItemCarrinhoRepositorio;
import br.com.popular.repositorio.ProdutoRepositorio;
import br.com.popular.entidades.ItemCarrinho;
import br.com.popular.entidades.Produto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarrinhoServico {

    @Autowired
    private  ItemCarrinhoRepositorio itemCarrinhoRepositorio;
    @Autowired
    private  ProdutoRepositorio produtoRepositorio;

    @Transactional
    public ItemCarrinho adicionarItem(Long produtoId, Integer quantidade) {
        Produto produto = (Produto) produtoRepositorio.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));


        if (quantidade > produto.getEstoque()) {
            throw new RuntimeException("Estoque insuficiente! Disponível: " + produto.getEstoque());
        }

        ItemCarrinho item = new ItemCarrinho(produto, quantidade);
        return itemCarrinhoRepositorio.save(item);
    }

    // Lista todos os itens do carrinho
    public List<ItemCarrinho> listarItens() {
        return itemCarrinhoRepositorio.findAll();
    }

    // Remove um item do carrinho
    @Transactional
    public void removerItem(Long itemId) {
        itemCarrinhoRepositorio.deleteById(itemId);
    }

    // Limpa o carrinho completamente
    @Transactional
    public void limparCarrinho() {
        itemCarrinhoRepositorio.deleteAll();
    }

    // (Opcional) Atualiza a quantidade de um item
    @Transactional
    public ItemCarrinho atualizarQuantidade(Long itemId, int novaQuantidade) {
        ItemCarrinho item = itemCarrinhoRepositorio.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        if (novaQuantidade > item.getProduto().getEstoque()) {
            throw new RuntimeException("Estoque insuficiente!");
        }

        item.setQuantidade(novaQuantidade);
        return itemCarrinhoRepositorio.save(item);
    }

    @Transactional
    public String finalizarCompra(CompraRequest compraRequest) {
        // Valida estoque
        compraRequest.getItens().forEach(item -> {
            Produto produto = produtoRepositorio.findById(item.getProdutoId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado: ID " + item.getProdutoId()));

            if (item.getQuantidade() > produto.getEstoque()) {
                throw new EstoqueInsuficienteException(
                        "Estoque insuficiente para " + produto.getNome() +
                                ". Disponível: " + produto.getEstoque()
                );
            }
        });

        // Atualiza estoque
        compraRequest.getItens().forEach(item -> {
            Produto produto = produtoRepositorio.findById(item.getProdutoId()).get();
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepositorio.save(produto);
        });

        // Limpa carrinho
        itemCarrinhoRepositorio.deleteAll();
        // Retorna número de pedido simulado
        return "PED-" + System.currentTimeMillis();
    }
}


