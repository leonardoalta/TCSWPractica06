/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package uv.org.TCSWPractica06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uv.org.TCSWPractica06.model.Producto;
import uv.org.TCSWPractica06.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */


@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return productoRepository.findById(id)
                .map(existing -> {
                    existing.setDescripcion(producto.getDescripcion());
                    existing.setPrecio(producto.getPrecio());
                    return ResponseEntity.ok(productoRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
