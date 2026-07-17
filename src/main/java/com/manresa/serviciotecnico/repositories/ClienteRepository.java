package com.manresa.serviciotecnico.repositories;

import com.manresa.serviciotecnico.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Spring Boot es tan inteligente que si nombrás el método así,
    // automáticamente te arma la query para buscar por email o teléfono:
    Cliente findByEmail(String email);
    Cliente findByTelefono(String telefono);
}