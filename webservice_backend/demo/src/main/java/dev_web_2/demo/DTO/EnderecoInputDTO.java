package dev_web_2.demo.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EnderecoInputDTO(@NotBlank String logradouro, String bairro, String cidade, String estado, int numero, long usuario_id) {
}
