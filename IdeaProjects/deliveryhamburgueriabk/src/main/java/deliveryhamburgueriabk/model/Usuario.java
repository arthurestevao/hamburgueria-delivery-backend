package deliveryhamburgueriabk.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "Endereço é obrigatório.")
    @Column(nullable = false)
    private String endereco;

    @NotBlank(message = "Telefone é obrigatório.")
    @Column(nullable = false)
    private String elefone;

}