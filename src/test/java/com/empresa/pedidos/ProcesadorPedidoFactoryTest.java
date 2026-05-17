package com.empresa.pedidos;

import com.empresa.pedidos.aplicacion.ProcesadorPedidoFactory;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProcesadorPedidoFactoryTest {

    @Autowired
    private ProcesadorPedidoFactory factory;

    @Test
    void debeRetornarProcesadorEstandar() {
        assertNotNull(factory.obtener(TipoPedido.ESTANDAR));
    }

    @Test
    void debeRetornarProcesadorExpress() {
        assertNotNull(factory.obtener(TipoPedido.EXPRESS));
    }

    @Test
    void debeRetornarProcesadorInternacional() {
        assertNotNull(factory.obtener(TipoPedido.INTERNACIONAL));
    }
}