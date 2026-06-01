package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.model.Entregador;
import java.util.List;

public interface IEntregadorService {
    Entregador cadastrar(String nome, String email, String senha, String telefone, String endereco, String veiculo);
    List<Entregador> listarTodos();
    Entregador buscarPorId(Long id);
    void remover(Long id);
}