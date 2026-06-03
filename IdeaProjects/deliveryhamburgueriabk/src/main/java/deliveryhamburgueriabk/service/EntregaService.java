package deliveryhamburgueriabk.service;

import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.service.interfaces.IEntregaService;
import deliveryhamburgueriabk.service.interfaces.IPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EntregaService implements IEntregaService {

    private static final Logger logger = LoggerFactory.getLogger(EntregaService.class);
    private final IPedidoService pedidoService;

    public EntregaService(IPedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @Override
    public Pedido sairParaEntrega(Long pedidoId) {
        logger.info("Pedido id={} saiu para entrega.", pedidoId);
        return pedidoService.atualizarStatus(pedidoId, StatusPedido.SAIU_PARA_ENTREGA);
    }

    @Override
    public Pedido confirmarEntrega(Long pedidoId) {
        return pedidoService.atualizarStatus(pedidoId, StatusPedido.ENTREGUE);
    }
}