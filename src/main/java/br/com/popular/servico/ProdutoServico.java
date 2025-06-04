package br.com.popular.servico;

import br.com.popular.repositorio.ProdutoRepositorio;
import br.com.popular.entidades.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServico {
    @Autowired
    private  ProdutoRepositorio produtoRepositorio;


    public Produto cadastrarProduto(Produto produto) {
        return produtoRepositorio.save(produto);
    }

    // Lista todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepositorio.findAll();
    }

    // Busca produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    // Atualiza um produto existente
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = produtoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setImagemUrl(produtoAtualizado.getImagemUrl());
        produto.setEstoque(produtoAtualizado.getEstoque());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setFabricante(produtoAtualizado.getFabricante());
        produto.setReceitaObrigatoria(produtoAtualizado.isReceitaObrigatoria());

        return produtoRepositorio.save(produto);
    }

    // Deleta um produto
    public void deletarProduto(Long id) {
        produtoRepositorio.deleteById(id);
    }

    // Filtros personalizados
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepositorio.findByCategoria(categoria);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepositorio.findByNomeContainingIgnoreCase(nome);
    }

}
