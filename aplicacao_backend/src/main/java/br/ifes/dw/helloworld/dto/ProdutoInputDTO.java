package br.ifes.dw.helloworld.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

//@Data
public record ProdutoInputDTO (@NotBlank String nome, @NotNull double preco){

}
