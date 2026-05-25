package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Usuario;

public class PedidoEntregaFactory implements IPedidoFactory{

    private static double TAXA_ENTREGA = 8.50;

    @Override
    public Pedido criarPedido(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) throws IllegalAccessException {

        if(enderecoEntrega == null || enderecoEntrega.isBlank()){
            throw new IllegalAccessException("Endereço de entrega é obrigatório.");

        }

        Pedido pedido = new Pedido(usuario, formaPagamento);
        pedido.setTipoPedido(TipoPedido.ENTREGA);
        pedido.setTaxaEntrega(TAXA_ENTREGA);
        pedido.setEnderecoEntrega(enderecoEntrega);
        return pedido;
    }

    @Override
    public Pedido criar(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) {
        return null;
    }
}