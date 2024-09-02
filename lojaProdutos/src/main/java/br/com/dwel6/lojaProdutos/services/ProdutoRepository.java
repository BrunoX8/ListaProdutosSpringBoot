package br.com.dwel6.lojaProdutos.services;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dwel6.lojaProdutos.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
