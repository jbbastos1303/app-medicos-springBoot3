package com.meuportifolio.apimedicosspring3.dto.medico;

import com.meuportifolio.apimedicosspring3.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco dadosEndereco) {
}
