package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PedidoEntregaFactory implements IPedidoFactory{

    private static double TAXA_ENTREGA = 2.00;

    @Override
    public Pedido criarPedido(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega){

        if(enderecoEntrega == null || enderecoEntrega.isBlank()){
            throw new IllegalArgumentException("Endereço de entrega é obrigatório.");
        }

        Pedido pedido = new Pedido(usuario, formaPagamento);
        pedido.setTipoPedido(TipoPedido.ENTREGA);
        pedido.setTaxaEntrega(TAXA_ENTREGA);
        pedido.setEnderecoEntrega(enderecoEntrega);
        return pedido;
    }
}