package deliveryhamburgueriabk.observer;

import deliveryhamburgueriabk.model.Pedido;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PedidoStatusEvent extends ApplicationEvent {

    private final Pedido pedido;

    public PedidoStatusEvent(Object source, Pedido pedido){
        super(source);
        this.pedido = pedido;
    }
}