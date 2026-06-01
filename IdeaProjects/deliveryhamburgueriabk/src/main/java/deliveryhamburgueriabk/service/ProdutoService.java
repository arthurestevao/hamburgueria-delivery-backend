package deliveryhamburgueriabk.service;

import deliveryhamburgueriabk.exception.RecursoNaoEncontradoException;
import deliveryhamburgueriabk.model.Produto;
import deliveryhamburgueriabk.repository.ProdutoRepository;
import deliveryhamburgueriabk.service.interfaces.IProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto criar(Produto produto) {
        produto.setTotalVendas(0);
        Produto salvo = produtoRepository.save(produto);
        logger.info("Produto criado: {} (id={})", salvo.getNome(), salvo.getId());
        return salvo;
    }

    @Override
    public Produto atualizar(Long id, Produto dadosNovos) {
        Produto produto = buscarPorId(id);
        produto.setNome(dadosNovos.getNome());
        produto.setPreco(dadosNovos.getPreco());
        produto.setDescricao(dadosNovos.getDescricao());
        produto.setDisponivel(dadosNovos.isDisponivel());
        logger.info("Produto atualizado: id={}", id);
        return produtoRepository.save(produto);
    }

    @Override
    public void remover(Long id) {
        buscarPorId(id);
        produtoRepository.deleteById(id);
        logger.info("Produto removido: id={}", id);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findByDisponivelTrue();
    }

    @Override
    public List<Produto> listarMaisPopulares() {
        return produtoRepository.findTop5ByOrderByTotalVendasDesc();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id));
    }
}