package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario cadastrar(Usuario usuario);
    Usuario login(String email, String senha);
    Usuario buscarPorId(Long id);
    List<Usuario> listarTodos();
}
