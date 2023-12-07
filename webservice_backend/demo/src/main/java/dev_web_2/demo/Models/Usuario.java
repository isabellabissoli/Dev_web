package dev_web_2.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private String nome;


    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate data_nascimento;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Endereco> endereco;
}
