package com.meuportifolio.apimedicosspring3.controller;

import com.meuportifolio.apimedicosspring3.dto.paciente.DadosAtualizacaoPaciente;
import com.meuportifolio.apimedicosspring3.dto.paciente.DadosCadastroPaciente;
import com.meuportifolio.apimedicosspring3.dto.paciente.DadosListagemPaciente;
import com.meuportifolio.apimedicosspring3.model.Paciente;
import com.meuportifolio.apimedicosspring3.repository.PacienteRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
@Slf4j
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {

        pacienteRepository.save(new Paciente(dados));
        log.info("As informações do médico foram cadastradas com sucesso: " + dados);
    }
    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        //return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}
