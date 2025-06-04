package br.com.popular.repositorio;

import br.com.popular.entidades.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemCarrinhoRepositorio extends JpaRepository<ItemCarrinho,Long> {
}
