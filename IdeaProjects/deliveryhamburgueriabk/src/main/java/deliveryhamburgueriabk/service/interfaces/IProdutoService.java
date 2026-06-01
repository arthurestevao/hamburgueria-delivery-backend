package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.model.Produto;
import jakarta.validation.Valid;

import java.util.List;

public interface IProdutoService {

    Produto criar(@Valid Produto request);
    Produto atualizar(Long id, @Valid Produto request);
    void remover(Long id);
    List<Produto> listarTodos();
    List<Produto> listarMaisPopulares();
    Produto buscarPorId(Long id);
}