package com.meuportifolio.apimedicosspring3.controller;

import com.meuportifolio.apimedicosspring3.dto.medico.DadosAtualizaMedico;
import com.meuportifolio.apimedicosspring3.dto.medico.DadosCadastroMedico;
import com.meuportifolio.apimedicosspring3.dto.medico.DadosListagemMedico;
import com.meuportifolio.apimedicosspring3.model.Medico;
import com.meuportifolio.apimedicosspring3.repository.MedicoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
        log.info("As informações do médico foram cadastradas com sucesso: " + dados);
    }
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort={"nome"}) Pageable paginacao){
        //return medicoRepository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList();
        //return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizaInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }
}
