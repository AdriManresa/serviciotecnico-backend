package com.manresa.serviciotecnico.models;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "ordenes_trabajo")
public class OrdenDeTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipo;            // Ej: "Notebook HP 15"
    private String fallaReportada;    // Ej: "No enciende"
    private String diagnostico;       // Tu nota técnica

    // Obligamos a que guarde el texto del Enum ("INGRESADO") y no un número
    @Enumerated(EnumType.STRING)
    private EstadoOrden estado;

    private LocalDate fechaIngreso;

    // MUCHAS órdenes pertenecen a UN cliente.
    // @JoinColumn crea la columna física "cliente_id" en esta tabla
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}