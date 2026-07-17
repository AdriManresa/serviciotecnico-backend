package com.manresa.serviciotecnico.controllers;

import com.manresa.serviciotecnico.models.EstadoOrden;
import com.manresa.serviciotecnico.models.OrdenDeTrabajo;
import com.manresa.serviciotecnico.services.OrdenDeTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController // Le dice a Spring que devuelva datos puros (JSON), ideal para Angular después
@RequestMapping("/api/ordenes") // Esta es la URL base. Todo va a entrar por localhost:8080/api/ordenes
public class OrdenDeTrabajoController {

    // Inyectamos nuestro servicio (el cerebro)
    @Autowired
    private OrdenDeTrabajoService ordenService;
    @PostMapping("/cliente/{idCliente}")
    public OrdenDeTrabajo crearOrden(
            @PathVariable Long idCliente, // Saca el ID directamente de la URL (ej: el "1")
            @RequestBody OrdenDeTrabajo nuevaOrden // Saca los datos del cuerpo de la petición (JSON)
    ) {

        //Le pasamos el paquete al Servicio
        return ordenService.registrarIngreso(nuevaOrden, idCliente);
    }

    @GetMapping
    public List<OrdenDeTrabajo> obtenerTodas() {
        return ordenService.listarTodas();
    }
    @GetMapping("{idOrden}")
    public OrdenDeTrabajo obtenerPorId(@PathVariable Long idOrden) {

        return ordenService.buscarPorId(idOrden);
    }

    // 3. Endpoint para actualizar el estado de una orden
    @PutMapping("/{idOrden}/estado")
    public OrdenDeTrabajo cambiarEstado(
            @PathVariable Long idOrden,
            @RequestParam EstadoOrden nuevoEstado // Lo recibimos como un parámetro extra en la URL
    ) {

        return ordenService.actualizarEstado(idOrden, nuevoEstado);
    }

// 4. Endpoint para eliminar una orden
    @DeleteMapping("/{idOrden}")
    public void borrarOrden(@PathVariable Long idOrden) {
        ordenService.eliminarOrden(idOrden);
    }
}