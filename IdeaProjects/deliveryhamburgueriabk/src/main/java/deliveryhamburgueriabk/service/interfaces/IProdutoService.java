package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.model.Produto;

import java.util.List;

public interface IProdutoService {

    Produto criar(Produto produto);
    Produto atualizar(Long id, Produto dadosNovos);
    void remover(Long id);
    List<Produto> listarTodos();
    List<Produto> listarMaisPopulares();
    Produto buscarPorId(Long id);
}