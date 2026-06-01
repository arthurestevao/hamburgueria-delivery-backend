package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.TipoPedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoFactoryProvider {

    private final PedidoEntregaFactory pedidoEntregaFactory;
    private final PedidoRetiradaFactory pedidoRetiradaFactory;

    public PedidoFactoryProvider(PedidoEntregaFactory pedidoEntregaFactory, PedidoRetiradaFactory pedidoRetiradaFactory){
        this.pedidoEntregaFactory = pedidoEntregaFactory;
        this.pedidoRetiradaFactory = pedidoRetiradaFactory;
    }

    public IPedidoFactory obter(TipoPedido tipo){
        return switch (tipo){
            case ENTREGA -> pedidoEntregaFactory;
            case RETIRADA -> pedidoRetiradaFactory;
        };
    }
}
