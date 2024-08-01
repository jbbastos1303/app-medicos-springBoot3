package com.meuportifolio.apimedicosspring3.dto.paciente;

import com.meuportifolio.apimedicosspring3.dto.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(Long id,
                                       String nome,
                                       String telefone,
                                       @Valid DadosEndereco endereco) {
}
