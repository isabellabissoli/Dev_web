package dev_web_2.demo.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public record UsuarioInputDTO (@NotBlank String nome, LocalDate data_nascimento){
}
