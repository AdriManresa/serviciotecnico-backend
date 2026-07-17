package com.manresa.serviciotecnico.services;

import com.manresa.serviciotecnico.models.Cliente;
import com.manresa.serviciotecnico.models.EstadoOrden;
import com.manresa.serviciotecnico.models.OrdenDeTrabajo;
import com.manresa.serviciotecnico.repositories.ClienteRepository;
import com.manresa.serviciotecnico.repositories.OrdenDeTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdenDeTrabajoService {
    // Inyectamos las herramientas para guardar/buscar en la base de datos
    @Autowired
    private OrdenDeTrabajoRepository ordenRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //método principal para cuando entra una compu al taller
    public OrdenDeTrabajo registrarIngreso(OrdenDeTrabajo nuevaOrden, Long idCliente) {

        nuevaOrden.setEstado(EstadoOrden.INGRESADO);
        nuevaOrden.setFechaIngreso(LocalDate.now());
        Cliente clienteEncontrado = clienteRepository.findById(idCliente).orElseThrow();
        nuevaOrden.setCliente(clienteEncontrado);
        return ordenRepository.save(nuevaOrden);
    }

    public List<OrdenDeTrabajo> listarTodas() {
        return ordenRepository.findAll();
    }
    public OrdenDeTrabajo buscarPorId(Long idOrden) {
        return ordenRepository.findById(idOrden).orElseThrow();
    }

    // Método para actualizar el estado (ej: pasar de INGRESADO a EN_REVISION)
    public OrdenDeTrabajo actualizarEstado(Long idOrden, EstadoOrden nuevoEstado) {

        // 1. Buscamos la orden
        OrdenDeTrabajo ordenEncontrada = this.buscarPorId(idOrden);

        // 2. Le modificamos el estado a esa orden
        ordenEncontrada.setEstado(nuevoEstado);

        // 3. Volvemos a guardarla en la base de datos
        return ordenRepository.save(ordenEncontrada);
    }

    // Método para eliminar una orden si nos equivocamos
    public void eliminarOrden(Long idOrden) {

        ordenRepository.deleteById(idOrden);
    }
}
