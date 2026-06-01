package deliveryhamburgueriabk.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoObserver {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoObserver.class);

    @EventListener
    public void onPedidoStatusAtualizado(PedidoStatusEvent evento){
        var pedido = evento.getPedido();

        String mensagemCliente = switch (pedido.getStatusPedido()){

            case PENDENTE -> "Seu pedido foi recebido.";
            case EM_PREPARO -> "Seu pedido está sendo preparado.";
            case PRONTO -> "Seu pedido está pronto, aguardando retirada";
            case SAIU_PARA_ENTREGA -> "Seu pedido saiu para entrega.";
            case ENTREGUE -> "Pedido entrega. Bom apetite.";
            case CANCELADO -> "Seu pedido foi cancelado.";
        };

        logger.info("[CLIENTE - {}] {}", pedido.getUsuario().getEmail(), mensagemCliente);
        logger.info("[ADMIN] Pedido #{} -> status: {}", pedido.getId(), pedido.getStatusPedido());
    }
}