/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uv.org.TCSWPractica06.model;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @JsonIgnore

    private Producto producto;

    @Column
    private int cantidad;

    @Column
    private BigDecimal precio;

    @Column
    private BigDecimal monto;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}
