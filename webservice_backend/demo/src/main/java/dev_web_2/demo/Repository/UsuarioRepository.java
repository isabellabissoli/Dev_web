package dev_web_2.demo.Repository;

import dev_web_2.demo.Models.Endereco;
import dev_web_2.demo.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
