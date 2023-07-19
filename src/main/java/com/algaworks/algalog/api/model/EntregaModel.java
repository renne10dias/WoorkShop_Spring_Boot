package com.algaworks.algalog.api.model;

import com.algaworks.algalog.model.StatusEntrega;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class EntregaModel {
    private Long id;
    private ClienteResumoModel cliente;
    private String nomeCliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime datafinalizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResumoModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoModel cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public DestinatarioModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DestinatarioModel destinatario) {
        this.destinatario = destinatario;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public OffsetDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(OffsetDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public OffsetDateTime getDatafinalizacao() {
        return datafinalizacao;
    }

    public void setDatafinalizacao(OffsetDateTime datafinalizacao) {
        this.datafinalizacao = datafinalizacao;
    }
}
