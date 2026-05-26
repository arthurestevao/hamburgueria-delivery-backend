package deliveryhamburgueriabk.observer;

import deliveryhamburgueriabk.model.Pedido;
import org.springframework.context.ApplicationEvent;


public class PedidoStatusEvent extends ApplicationEvent {

    private final Pedido pedido;

    public PedidoStatusEvent(Object source, Pedido pedido){
        super(source);
        this.pedido = pedido;
    }

    public Pedido getPedido(){
        return pedido;
    }
}