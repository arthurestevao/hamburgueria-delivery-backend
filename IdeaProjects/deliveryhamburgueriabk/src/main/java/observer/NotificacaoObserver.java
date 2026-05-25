package observer;

import event.PedidoStatusEvent;
import org.springframework.context.event.EventListener;

import static deliveryhamburgueriabk.enums.StatusPedido.*;

public class NotificacaoObserver {

    @EventListener
    public void onPedidoStatusAtualizado(PedidoStatusEvent event){
        var pedido = event.getPedido();

        String mensagemCliente = switch (pedido.getStatusPedido()){

            case PENDENTE -> "Seu pedido foi recebido.";
            case EM_PREPARO -> "Seu pedido está sendo preparado.";
            case SAIU_PARA_ENTREGA -> "Seu pedido saiu para entrega.";
            case ENTREGUE -> "Pedido entrega. Bom apetite.";
            case CANCELADO -> "Seu pedido foi cancelado.";
        };

        System.out.println("[NOTIFICAÇÃO - CLIENTE " + pedido.getUsuario() + "] " + mensagemCliente);
        System.out.println("[NOTIFICACAO - ADMIN] Pedido: " + pedido.getId() + " atualizado para: " + pedido.getStatusPedido());
    }
}
