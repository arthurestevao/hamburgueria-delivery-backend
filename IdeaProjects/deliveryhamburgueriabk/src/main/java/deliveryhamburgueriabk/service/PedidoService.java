package deliveryhamburgueriabk.service;
import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.service.interfaces.IPedidoService;

import java.util.List;

public class PedidoService implements IPedidoService {
    @Override
    public Pedido criar(Long usuarioId, TipoPedido tipoPedido, FormaPagamento formaPagamento, String enderecoEntrega, List<Long> produtoIds, String codigoCupom) {
        return null;
    }

    @Override
    public Pedido atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        return null;
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Pedido> historicoPorUsuario(Long usuarioId) {
        return List.of();
    }
}