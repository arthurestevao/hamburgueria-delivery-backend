package deliveryhamburgueriabk.service;
import deliveryhamburgueriabk.model.Usuario;
import deliveryhamburgueriabk.service.interfaces.IUsuarioService;

import java.util.List;

public class UsuarioService implements IUsuarioService {

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario login(String email, String senha) {
        return null;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        return List.of();
    }
}