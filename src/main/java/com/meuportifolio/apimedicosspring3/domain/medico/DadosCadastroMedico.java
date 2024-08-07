package com.meuportifolio.apimedicosspring3.domain.medico;

import com.meuportifolio.apimedicosspring3.domain.endereco.DadosEndereco;
import com.meuportifolio.apimedicosspring3.enumeration.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico (
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,
        @NotNull(message = "{especialidade.obrigatoria}")
        Especialidade especialidade,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DadosEndereco endereco
){
}
