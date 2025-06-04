package br.com.popular.controle;

import br.com.popular.servico.ProdutoServico;
import br.com.popular.entidades.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoControle {

    @Autowired
    private  ProdutoServico produtoServico;

 @PostMapping
 public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        var cadastrar = produtoServico.cadastrarProduto(produto);
        return new ResponseEntity<>(cadastrar, HttpStatus.CREATED);
}
@GetMapping
public ResponseEntity<List<Produto>>listarProdutos(){
        var listar = produtoServico.listarProdutos();
      return new ResponseEntity<>(listar,HttpStatus.OK);
 }

 @GetMapping("/{id}")
 public ResponseEntity<Produto>buscarPorId(@PathVariable Long id){
        var buscar = produtoServico.buscarPorId(id);
        return new ResponseEntity<>(buscar,HttpStatus.OK);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Produto>atualizarProduto(@PathVariable Long id,@RequestBody Produto produto){
        var atualizar = produtoServico.atualizarProduto(id,produto);
 return new ResponseEntity<>(atualizar,HttpStatus.OK);
 }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable String categoria) {
        var buscar =  produtoServico.buscarPorCategoria(categoria);
        return new ResponseEntity<>(buscar,HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        var buscar =  produtoServico.buscarPorNome(nome);
        return new ResponseEntity<>(buscar,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletarProduto(@PathVariable Long id) {
        produtoServico.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
