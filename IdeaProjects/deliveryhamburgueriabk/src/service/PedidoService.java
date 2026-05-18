package service;
import model.Pedido;

public class PedidoService {
    public void finalizarPedido(Pedido pedido) {

        System.out.println("Pedido finalizado");
        System.out.println("Total: R$ " + pedido.calcularTotal());
    }
}