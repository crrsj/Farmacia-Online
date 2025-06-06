package br.com.popular.controle;

import br.com.popular.dto.CompraRequest;
import br.com.popular.servico.ItemCarrinhoServico;
import br.com.popular.entidades.ItemCarrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoControle {

    @Autowired
    private ItemCarrinhoServico itemCarrinhoServico;

    @PostMapping("/adicionar")
    public ResponseEntity<ItemCarrinho> adicionarItem(@RequestParam Long produtoId,
                                                      @RequestParam Integer quantidade) {
        var carrinho = itemCarrinhoServico.adicionarItem(produtoId, quantidade);
        return new ResponseEntity<>(carrinho, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrinho>> listarItens() {
        var listar = itemCarrinhoServico.listarItens();
        return new ResponseEntity<>(listar, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long itemId) {
        itemCarrinhoServico.removerItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<Void> limparCarrinho() {
        itemCarrinhoServico.limparCarrinho();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/atualizar/{itemId}")
    public ResponseEntity<ItemCarrinho> atualizarQuantidade(
            @PathVariable Long itemId,
            @RequestParam Integer quantidade) {
        var atualizar = itemCarrinhoServico.atualizarQuantidade(itemId, quantidade);
        return new ResponseEntity<>(atualizar, HttpStatus.OK);
    }

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarCompra(@RequestBody CompraRequest compraRequest) {
        try {
            String numeroPedido = itemCarrinhoServico.finalizarCompra(compraRequest);
            return ResponseEntity.ok("Compra finalizada! Nº Pedido: " + numeroPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

  }
