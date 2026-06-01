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

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;

    @Column(nullable = false)
    private double taxaEntrega;

    @Column
    private String enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    public Pedido(Usuario usuario, FormaPagamento formaPagamento) {
        this.usuario = usuario;
        this.formaPagamento = formaPagamento;
        this.statusPedido = StatusPedido.PENDENTE;
        this.criadoEm = LocalDateTime.now();
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        item.setPedido(this);
        valorTotal += item.getSubtotal();
    }

}