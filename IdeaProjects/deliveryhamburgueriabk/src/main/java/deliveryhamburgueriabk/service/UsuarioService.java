package deliveryhamburgueriabk.service;
import deliveryhamburgueriabk.model.Usuario;

public class UsuarioService {

    public void cadastrarUsuario(Usuario usuario) {

        System.out.println("Usuari cadastrado com sucesso");
        System.out.println("Nome: " + usuario.getNome());
    }
}