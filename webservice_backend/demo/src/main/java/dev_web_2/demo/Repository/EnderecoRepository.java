package dev_web_2.demo.Repository;

import dev_web_2.demo.Models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
