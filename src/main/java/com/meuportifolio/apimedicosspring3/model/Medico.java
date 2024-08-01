package com.meuportifolio.apimedicosspring3.model;

import com.meuportifolio.apimedicosspring3.dto.medico.DadosAtualizaMedico;
import com.meuportifolio.apimedicosspring3.dto.medico.DadosCadastroMedico;
import com.meuportifolio.apimedicosspring3.dto.enumeration.Especialidade;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaInformacoes(DadosAtualizaMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.dadosEndereco() != null) {
            this.endereco.atualizarInformacoes(dados.dadosEndereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
