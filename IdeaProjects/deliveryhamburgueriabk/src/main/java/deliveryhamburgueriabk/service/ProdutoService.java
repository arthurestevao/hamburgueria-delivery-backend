package deliveryhamburgueriabk.service;

import deliveryhamburgueriabk.model.Produto;
import deliveryhamburgueriabk.service.interfaces.IProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IProdutoService {
    @Override
    public Produto criar(Produto produto) {
        return null;
    }

    @Override
    public Produto atualizar(Long id, Produto dadosNovos) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }

    @Override
    public List<Produto> listarTodos() {
        return List.of();
    }

    @Override
    public List<Produto> listarMaisPopulares() {
        return List.of();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return null;
    }
}
