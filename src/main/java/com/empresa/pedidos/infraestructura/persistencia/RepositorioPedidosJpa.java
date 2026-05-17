package com.empresa.pedidos.infraestructura.persistencia;

import com.empresa.pedidos.dominio.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPedidosJpa
        extends JpaRepository<Pedido, Long> {
}