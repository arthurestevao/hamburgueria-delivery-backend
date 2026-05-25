package deliveryhamburgueriabk.model;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(nullable = false)
    private Pedido pedido;

    @ManyToOne
    @Column(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double subtotal;

    public double calcularSubtotal() {
        if (this.produto != null) {
            return this.produto.getPreco() * this.quantidade;
        }
        return 0.0;
    }
}