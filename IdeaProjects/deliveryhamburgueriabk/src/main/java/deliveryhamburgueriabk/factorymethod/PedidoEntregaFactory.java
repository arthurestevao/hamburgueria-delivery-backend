package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.exception.RegraDeNegocioException;
import deliveryhamburgueriabk.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoEntregaFactory implements IPedidoFactory {

    @Override
    public IPedido criarPedido(Pedido pedido) {
        if (pedido.getEnderecoEntrega() == null || pedido.getEnderecoEntrega().isBlank()) {
            throw new RegraDeNegocioException("Endereço de entrega é obrigatório para pedidos de entrega.");
        }
        return new PedidoEntrega(pedido);
    }
}