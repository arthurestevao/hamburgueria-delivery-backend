package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Usuario;

public interface IPedidoFactory {
    Pedido criarPedido(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega);
}