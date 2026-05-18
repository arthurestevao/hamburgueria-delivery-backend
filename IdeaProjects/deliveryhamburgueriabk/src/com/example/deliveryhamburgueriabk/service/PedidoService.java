package com.example.deliveryhamburgueriabk.service;
import com.example.deliveryhamburgueriabk.model.Pedido;

public class PedidoService {
    public void finalizarPedido(Pedido pedido) {

        System.out.println("Pedido finalizado");
        System.out.println("Total: R$ " + pedido.calcularTotal());
    }
}