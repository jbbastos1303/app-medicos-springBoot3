package com.meuportifolio.apimedicosspring3.domain.medico;

import com.meuportifolio.apimedicosspring3.domain.enumeration.Especialidade;
import com.meuportifolio.apimedicosspring3.model.Endereco;
import com.meuportifolio.apimedicosspring3.model.Medico;

public record DadosDetalhamentoMedico (Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco){
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
