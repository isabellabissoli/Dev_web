package dev_web_2.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private String logradouro;

    private String bairro;
    private String cidade;

    private String estado;

    private int numero;

    @ManyToOne
    private Usuario usuario;
}
