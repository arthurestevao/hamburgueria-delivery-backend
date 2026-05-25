package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Usuario;

public class PedidoRetiradaFactory implements IPedidoFactory {

    @Override
    public Pedido criarPedido(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) {

        Pedido pedido = new Pedido(usuario, formaPagamento);
        pedido.setTipoPedido(TipoPedido.RETIRADA);
        pedido.setTaxaEntrega(0.0);
        return pedido;
    }

    @Override
    public Pedido criar(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) {
        return null;
    }
}