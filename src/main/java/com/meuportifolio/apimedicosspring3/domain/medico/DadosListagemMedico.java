package com.meuportifolio.apimedicosspring3.domain.medico;

import com.meuportifolio.apimedicosspring3.enumeration.Especialidade;
import com.meuportifolio.apimedicosspring3.model.Medico;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
)
{
    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
