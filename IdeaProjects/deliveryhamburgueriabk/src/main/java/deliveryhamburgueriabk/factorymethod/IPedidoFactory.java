package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public interface IPedidoFactory {
    Pedido criarPedido(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega) throws IllegalAccessException;

    Pedido criar(Usuario usuario, FormaPagamento formaPagamento, String enderecoEntrega);
}