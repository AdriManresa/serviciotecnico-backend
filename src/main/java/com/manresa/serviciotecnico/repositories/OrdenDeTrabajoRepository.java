package com.manresa.serviciotecnico.repositories;
import com.manresa.serviciotecnico.models.EstadoOrden;
import com.manresa.serviciotecnico.models.OrdenDeTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {

    // Un método súper útil para el taller: buscar todas las órdenes según su estado
    List<OrdenDeTrabajo> findByEstado(EstadoOrden estado);
}