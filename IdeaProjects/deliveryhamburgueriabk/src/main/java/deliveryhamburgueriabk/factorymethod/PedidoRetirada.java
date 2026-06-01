package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;

public class PedidoRetirada implements IPedido {

    private final Pedido pedido;

    public PedidoRetirada(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.setTipoPedido(TipoPedido.RETIRADA);
        this.pedido.setTaxaEntrega(0.0);
    }

    @Override
    public void processar() {
        System.out.println("Pedido #" + pedido.getId() + " para RETIRADA processado.");
        System.out.println("Pronto em: " + calcularTempoDePreparo() + " minutos");
    }

    @Override
    public double calcularFrete() {
        return 0;
    }

    @Override
    public int calcularTempoDePreparo() {
        return 30;
    }

    @Override
    public String descTipo() {
        return "Retirada";
    }
}