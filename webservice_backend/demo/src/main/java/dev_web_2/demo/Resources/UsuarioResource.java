package dev_web_2.demo.Resources;

import dev_web_2.demo.DTO.UsuarioInputDTO;
import dev_web_2.demo.Models.Usuario;
import dev_web_2.demo.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public @ResponseBody ResponseEntity<List<Usuario>> listaUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody UsuarioInputDTO usuarioInputDTO){
        var usuarios = new Usuario();

        usuarios.setData_nascimento(usuarioInputDTO.data_nascimento());
        BeanUtils.copyProperties(usuarioInputDTO, usuarios);
        return usuarioRepository.save(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable(value = "id") long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Removido");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTipoEpico(@PathVariable long id,
                                                     @RequestBody @Valid UsuarioInputDTO usuarioInputDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);


        var usuarios = usuario.get();

        BeanUtils.copyProperties(usuarioInputDTO, usuarios);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarios));
    }
}
