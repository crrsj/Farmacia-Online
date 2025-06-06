package br.com.popular.repositorio;

import br.com.popular.entidades.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ItemCarrinhoRepositorio extends JpaRepository<ItemCarrinho,Long> {
    @Modifying
    @Query("UPDATE ItemCarrinho i SET i.quantidade = :quantidade WHERE i.id = :itemId")
    void atualizarQuantidade(@Param("itemId") Long itemId, @Param("quantidade") Integer quantidade);
}

