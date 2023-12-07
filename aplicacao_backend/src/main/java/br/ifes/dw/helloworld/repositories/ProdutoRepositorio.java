package br.ifes.dw.helloworld.repositories;

import br.ifes.dw.helloworld.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {
}
