package com.manresa.serviciotecnico.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    // Un cliente puede tener MUCHAS órdenes.
    // mappedBy = "cliente" significa que la relación ya está configurada en la otra clase (en la variable llamada "cliente")
    // cascade = CascadeType.ALL hace que si borrás al cliente, se borren sus órdenes (para no dejar datos huérfanos)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrdenDeTrabajo> ordenes = new ArrayList<>();

    // Constructor vacío exigido por JPA
    public Cliente() {}


}