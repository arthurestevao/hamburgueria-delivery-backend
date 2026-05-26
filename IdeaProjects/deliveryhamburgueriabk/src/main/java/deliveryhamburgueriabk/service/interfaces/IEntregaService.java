package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.model.Pedido;

public interface IEntregaService {

    Pedido sairParaEntrega(Long pedidoId);
    Pedido confirmarEntrega(Long pedidoId);
}