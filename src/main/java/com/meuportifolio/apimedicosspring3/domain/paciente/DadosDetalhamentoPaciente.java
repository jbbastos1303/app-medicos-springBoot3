package com.meuportifolio.apimedicosspring3.domain.paciente;

import com.meuportifolio.apimedicosspring3.model.Endereco;
import com.meuportifolio.apimedicosspring3.model.Paciente;

public record DadosDetalhamentoPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
