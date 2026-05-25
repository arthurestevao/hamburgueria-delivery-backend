package deliveryhamburgueriabk.model;
import deliveryhamburgueriabk.enums.TipoPedido;
import jakarta.persistence.*;
import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private int numeroPedido;

    @Column(nullable = false)
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPedido tipoPedido;

    @Column(nullable = false)
    private double taxaEntrega;

    @Column(nullable = false)
    private String enderecoEntrega;

    @ManyToMany
    @Column(nullable = false)
    private Cupom cupom;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    public Pedido(Usuario usuario, FormaPagamento formaPagamento) {
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        item.setPedido(this);
        valorTotal += item.getSubtotal();
    }
}