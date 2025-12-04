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
import java.util.List;

@Table
@Entity(name = "producto")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descripcion;

    @Column
    private double precio;
    
    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detalles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }
}

