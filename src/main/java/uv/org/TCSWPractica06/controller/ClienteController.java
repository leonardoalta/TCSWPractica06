/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package uv.org.TCSWPractica06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uv.org.TCSWPractica06.model.Cliente;
import uv.org.TCSWPractica06.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@RestController
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(cliente.getNombre());
                    existing.setDireccion(cliente.getDireccion());
                    existing.setTelefono(cliente.getTelefono());
                    return ResponseEntity.ok(clienteRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
