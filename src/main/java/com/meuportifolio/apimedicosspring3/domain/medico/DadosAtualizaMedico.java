package com.meuportifolio.apimedicosspring3.domain.medico;

import com.meuportifolio.apimedicosspring3.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco dadosEndereco) {
}
