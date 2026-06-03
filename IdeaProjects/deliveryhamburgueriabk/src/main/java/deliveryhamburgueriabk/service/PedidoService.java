package deliveryhamburgueriabk.service;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.exception.RecursoNaoEncontradoException;
import deliveryhamburgueriabk.exception.RegraDeNegocioException;
import deliveryhamburgueriabk.factorymethod.IPedido;
import deliveryhamburgueriabk.factorymethod.PedidoFactoryProvider;
import deliveryhamburgueriabk.model.*;
import deliveryhamburgueriabk.observer.PedidoStatusEvent;
import deliveryhamburgueriabk.repository.CupomRepository;
import deliveryhamburgueriabk.repository.PedidoRepository;
import deliveryhamburgueriabk.repository.ProdutoRepository;
import deliveryhamburgueriabk.repository.UsuarioRepository;
import deliveryhamburgueriabk.service.interfaces.IPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final CupomRepository cupomRepository;
    private final PedidoFactoryProvider factoryProvider;
    private final ApplicationEventPublisher eventPublisher;

    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository,
                         ProdutoRepository produtoRepository,
                         CupomRepository cupomRepository,
                         PedidoFactoryProvider factoryProvider,
                         ApplicationEventPublisher eventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.cupomRepository = cupomRepository;
        this.factoryProvider = factoryProvider;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Pedido criar(Long usuarioId, TipoPedido tipoPedido, FormaPagamento formaPagamento,
                        String enderecoEntrega, List<Long> produtoIds, String codigoCupom) {

        Usuario usuario = buscarUsuario(usuarioId);

        Pedido pedido = new Pedido(usuario, formaPagamento);
        pedido.setEnderecoEntrega(enderecoEntrega);

        adicionarItensPedido(pedido, produtoIds);

        IPedido iPedido = factoryProvider.obter(tipoPedido).criarPedido(pedido);
        iPedido.processar();

        if(codigoCupom != null && !codigoCupom.isBlank()){
            aplicarCupom(codigoCupom, pedido);
        }

        pedido.setValorTotal(pedido.getValorTotal() + pedido.getTaxaEntrega());

        Pedido salvo = pedidoRepository.save(pedido);
        logger.info("Pedido criado: id={}, usuario={}, tipo={}, subtotal=R${}, frete=R${}, total=R${}",
                salvo.getId(), usuario.getEmail(), tipoPedido,
                salvo.getValorTotal(), salvo.getTaxaEntrega(), salvo.calcularTotal());

        eventPublisher.publishEvent(new PedidoStatusEvent(this, salvo));
        return salvo;
    }

    @Override
    @Transactional
    public Pedido atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(pedidoId);
        pedido.setStatusPedido(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);
        logger.info("Status do pedido id={} atualizado para: {}", pedidoId, novoStatus);
        eventPublisher.publishEvent(new PedidoStatusEvent(this, atualizado));
        return atualizado;
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado: " + id));
    }

    @Override
    public List<Pedido> historicoPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatusPedido(status);
    }

    @Override
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // métodos privados

    private Usuario buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado: " + usuarioId));
    }

    private void adicionarItensPedido(Pedido pedido, List<Long> produtoIds) {
        for (Long produtoId : produtoIds) {
            Produto produto = produtoRepository.findById(produtoId)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + produtoId));
            if (!produto.isDisponivel()) {
                throw new RegraDeNegocioException("Produto indisponível: " + produto.getNome());
            }
            pedido.adicionarItem(new ItemPedido(produto, 1));
            produto.setTotalVendas(produto.getTotalVendas() + 1);
            produtoRepository.save(produto);
        }
    }

    private void aplicarCupom(String codigo, Pedido pedido) {
        Cupom cupom = cupomRepository.findByCodigoAndAtivoTrue(codigo)
                .orElseThrow(() -> new RegraDeNegocioException("Cupom inválido ou expirado."));
        if (cupom.getValidade().isBefore(LocalDateTime.now())) {
            throw new RegraDeNegocioException("Cupom expirado.");
        }
        if (cupom.isFreteGratis()) {
            pedido.setTaxaEntrega(0.0);
        }
        double desconto = pedido.getValorTotal() * (cupom.getDesconto() / 100);
        pedido.setValorTotal(pedido.getValorTotal() - desconto);
        pedido.setCupom(cupom);
    }
}