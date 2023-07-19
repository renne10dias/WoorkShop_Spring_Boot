package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.model.Entrega;
import org.springframework.stereotype.Service;

@Service
public class BuscaEntregaService {
    private EntregaRepository entregaRepository;

    // construtor
    public BuscaEntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega buscar(Long entregaId){
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
