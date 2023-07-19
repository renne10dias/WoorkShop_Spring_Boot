package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import com.algaworks.algalog.model.Cliente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    public ClienteController(ClienteRepository clienteRepository, CatalogoClienteService catalogoClienteService) {
        this.clienteRepository = clienteRepository;
        this.catalogoClienteService = catalogoClienteService;
    }
// Método Construtor



    // URLs
    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok) // CÓDIGO HTTP
                .orElse(ResponseEntity.notFound().build());  // CÓDIGO HTTP

        /*Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build(); */
    }
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)  // CÓDIGO HTTP
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return catalogoClienteService.salvar(cliente);
    }



    @PutMapping("clientes/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build(); // CÓDIGO HTTP
        }
        cliente.setId(clienteId);
        cliente = catalogoClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente); // CÓDIGO HTTP
    }



    @DeleteMapping("clientes/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();  // CÓDIGO HTTP
        }
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build(); // CÓDIGO HTTP
    }
}
