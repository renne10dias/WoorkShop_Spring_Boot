package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.model.Cliente;
import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.model.StatusEntrega;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.OffsetDateTime;


@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    public SolicitacaoEntregaService(CatalogoClienteService catalogoClienteService, EntregaRepository entregaRepository) {
        this.catalogoClienteService = catalogoClienteService;
        this.entregaRepository = entregaRepository;
    }

    //getCliente --> faz uma consulta nessa variavel privada
    //setCliente --> faz uma atualização nessa variavel privada
    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }
}
