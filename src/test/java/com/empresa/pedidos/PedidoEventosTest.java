package com.empresa.pedidos;

import com.empresa.pedidos.aplicacion.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
public class PedidoEventosTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    void debePublicarEvento() {

        Pedido pedido = new Pedido();

        publisher.publishEvent(
                new PedidoProcesadoEvent(pedido)
        );
    }
}