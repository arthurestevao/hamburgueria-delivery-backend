package deliveryhamburgueriabk.model;

import jakarta.persistence.*;

@Entity
@Table(name = "entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false)
    private String veiculo;

    public Entregador(){}

    public Entregador(String nome, String telefone, String veiculo) {
        this.nome = nome;
        this.telefone = telefone;
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getVeiculo() {
        return veiculo;
    }
}