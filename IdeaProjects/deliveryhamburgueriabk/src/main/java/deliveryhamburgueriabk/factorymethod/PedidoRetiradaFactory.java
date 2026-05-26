package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PedidoRetiradaFactory implements IPedidoFactory {

    @Override
    public Pedido criarPedido(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) {

        Pedido pedido = new Pedido(usuario, formaPagamento);
        pedido.setTipoPedido(TipoPedido.RETIRADA);
        pedido.setTaxaEntrega(0.0);
        return pedido;
    }
}