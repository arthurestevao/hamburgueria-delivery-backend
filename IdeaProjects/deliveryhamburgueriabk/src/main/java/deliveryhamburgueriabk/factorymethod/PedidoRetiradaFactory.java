package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoRetiradaFactory implements IPedidoFactory {

    @Override
    public IPedido criarPedido(Pedido pedido) {
        return new PedidoRetirada(pedido);
    }
}