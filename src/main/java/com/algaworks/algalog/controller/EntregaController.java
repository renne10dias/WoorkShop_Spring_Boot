package com.algaworks.algalog.controller;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalog.model.Entrega;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntregaController {
    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    public EntregaController(EntregaRepository entregaRepository, SolicitacaoEntregaService solicitacaoEntregaService, EntregaAssembler entregaAssembler, FinalizacaoEntregaService finalizacaoEntregaService) {
        this.entregaRepository = entregaRepository;
        this.solicitacaoEntregaService = solicitacaoEntregaService;
        this.entregaAssembler = entregaAssembler;
        this.finalizacaoEntregaService = finalizacaoEntregaService;
    }

    @PostMapping("/entregas")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solocitar(@Valid @RequestBody EntregaInput entregaInput){
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolocitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolocitada);
    }

    @PutMapping("/entregas/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping("/entregas")
    public List<EntregaModel> listar(){
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/entregas/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }
}
