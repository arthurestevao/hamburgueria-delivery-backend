package deliveryhamburgueriabk.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cupons")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private double desconto;

    @Column(nullable = false)
    private boolean freteGratis;

    @Column(nullable = false)
    private LocalDateTime validade;

    @Column(nullable = false)
    private boolean ativo;

}