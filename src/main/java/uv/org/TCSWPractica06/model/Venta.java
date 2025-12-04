/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uv.org.TCSWPractica06.model;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Table
@Entity(name = "venta")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column
    private BigDecimal montoTotal;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }
}
