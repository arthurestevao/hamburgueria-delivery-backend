package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;

import java.util.List;

public interface IPedidoService {
    Pedido criar(Long usuarioId, TipoPedido tipoPedido, FormaPagamento formaPagamento, String enderecoEntrega, List<Long> produtoIds, String codigoCupom);
    Pedido atualizarStatus(Long pedidoId, StatusPedido novoStatus);
    Pedido buscarPorId(Long id);
    List<Pedido> historicoPorUsuario(Long usuarioId);
    List<Pedido> listarPorStatus(StatusPedido status);
}