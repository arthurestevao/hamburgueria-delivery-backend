package deliveryhamburgueriabk.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private double desconto;

    public Cupom(){}

    public Cupom(String codigo, double desconto) {
        this.codigo = codigo;
        this.desconto = desconto;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getDesconto() {
        return desconto;
    }
}