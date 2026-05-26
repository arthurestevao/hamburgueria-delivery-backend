package deliveryhamburgueriabk.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @Positive(message = "O preço deve ser maior que zero.")
    @Column(nullable = false)
    private double preco;

    @Column(length = 300)
    private String descricao;

    @Column(nullable = false)
    private boolean disponivel = true;

    @Column(name = "total_vendas", nullable = false)
    private int totalVendas = 0;

}