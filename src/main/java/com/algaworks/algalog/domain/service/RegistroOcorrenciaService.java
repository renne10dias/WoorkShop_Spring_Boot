package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.model.Ocorrencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    // construtor
    public RegistroOcorrenciaService(BuscaEntregaService buscaEntregaService) {
        this.buscaEntregaService = buscaEntregaService;
    }

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }

    public BuscaEntregaService getBuscaEntregaService() {
        return buscaEntregaService;
    }

    public void setBuscaEntregaService(BuscaEntregaService buscaEntregaService) {
        this.buscaEntregaService = buscaEntregaService;
    }
}
