/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package uv.org.TCSWPractica06.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import uv.org.TCSWPractica06.model.*;
import uv.org.TCSWPractica06.repository.ClienteRepository;
import uv.org.TCSWPractica06.repository.DetalleVentaRepository;
import uv.org.TCSWPractica06.repository.ProductoRepository;
import uv.org.TCSWPractica06.repository.VentaRepository;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @GetMapping
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {
        return ventaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody VentaDTO dto) {
        dto.calcularMontoTotal();

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(dto.getFecha());
        venta.setMontoTotal(dto.getMontoTotal());

        Venta ventaGuardada = ventaRepository.save(venta);

        List<DetalleVenta> detalles = new ArrayList<>();
        for (VentaDTO.DetalleVentaDTO d : dto.getDetalles()) {
            Producto producto = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(ventaGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecio(d.getPrecio());
            detalle.setMonto(d.getMonto());

            detalles.add(detalleVentaRepository.save(detalle));
        }

        ventaGuardada.setDetalles(detalles);
        return ResponseEntity.ok(ventaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
