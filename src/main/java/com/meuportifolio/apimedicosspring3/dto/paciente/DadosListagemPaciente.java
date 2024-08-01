package com.meuportifolio.apimedicosspring3.dto.paciente;

import com.meuportifolio.apimedicosspring3.model.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
