/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package uv.org.TCSWPractica06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uv.org.TCSWPractica06.model.Cliente;

/**
 *
 * @author leo
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
