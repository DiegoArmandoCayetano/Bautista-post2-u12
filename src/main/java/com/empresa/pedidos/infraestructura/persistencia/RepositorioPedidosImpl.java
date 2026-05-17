package com.empresa.pedidos.infraestructura.persistencia;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RepositorioPedidosImpl
        implements RepositorioPedidos {

    private final RepositorioPedidosJpa jpa;

    public RepositorioPedidosImpl(
            RepositorioPedidosJpa jpa
    ) {
        this.jpa = jpa;
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return jpa.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return jpa.findById(id);
    }
}