package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.model.StatusEntrega;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    public FinalizacaoEntregaService(EntregaRepository entregaRepository, BuscaEntregaService buscaEntregaService) {
        this.entregaRepository = entregaRepository;
        this.buscaEntregaService = buscaEntregaService;
    }

    @Transactional
    public void finalizar(Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }

}
