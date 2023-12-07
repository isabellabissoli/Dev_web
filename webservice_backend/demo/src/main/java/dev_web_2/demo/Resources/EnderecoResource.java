package dev_web_2.demo.Resources;

import dev_web_2.demo.DTO.EnderecoInputDTO;
import dev_web_2.demo.Models.Endereco;
import dev_web_2.demo.Models.Usuario;
import dev_web_2.demo.Repository.EnderecoRepository;
import dev_web_2.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;


import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public @ResponseBody Iterable<Endereco> listaUsuarios(){
        Iterable<Endereco> listaEndereco = enderecoRepository.findAll();
        return listaEndereco;
    }

    @PostMapping
    public Endereco criarEndereco(@RequestBody EnderecoInputDTO enderecoInputDTO){

        var endereco = new Endereco();
        Long usuarioId = enderecoInputDTO.usuario_id();


        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

        endereco.setUsuario(usuarioOptional.get());
        BeanUtils.copyProperties(enderecoInputDTO, endereco);

        return enderecoRepository.save(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> ataualizaeEndereco(
            @PathVariable long id,
            @RequestBody EnderecoInputDTO enderecoInputDTO) {


        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        Endereco endereco = enderecoOptional.get();

        Long usuarioId = enderecoInputDTO.usuario_id();
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);


        endereco.setUsuario(usuarioOptional.get());


        BeanUtils.copyProperties(enderecoInputDTO, endereco);

        Endereco enderecoAtualizado = enderecoRepository.save(endereco);

        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable(value = "id") long id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        enderecoRepository.delete(endereco.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereço Removido");

    }
}
