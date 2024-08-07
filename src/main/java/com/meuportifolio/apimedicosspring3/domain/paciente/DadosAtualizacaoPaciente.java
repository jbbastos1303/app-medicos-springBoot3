package com.meuportifolio.apimedicosspring3.domain.paciente;

import com.meuportifolio.apimedicosspring3.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(Long id,
                                       String nome,
                                       String telefone,
                                       @Valid DadosEndereco endereco) {
}
