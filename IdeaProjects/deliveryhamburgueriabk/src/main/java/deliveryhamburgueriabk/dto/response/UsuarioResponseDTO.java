package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.enums.Perfil;
import deliveryhamburgueriabk.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String endereco,
        String telefone,
        Perfil perfil
) {
    public static UsuarioResponseDTO de(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getEndereco(),
                usuario.getTelefone(),
                usuario.getPerfil()
        );
    }

    public @NotBlank(message = "Senha é obrigatória.") @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.") String senha() {
        return "";
    }
}