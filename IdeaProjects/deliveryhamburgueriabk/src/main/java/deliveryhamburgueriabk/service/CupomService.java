package deliveryhamburgueriabk.service;

import deliveryhamburgueriabk.exception.RecursoNaoEncontradoException;
import deliveryhamburgueriabk.exception.RegraDeNegocioException;
import deliveryhamburgueriabk.model.Cupom;
import deliveryhamburgueriabk.repository.CupomRepository;
import deliveryhamburgueriabk.service.interfaces.ICupomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class CupomService implements ICupomService {

    private static final Logger logger = LoggerFactory.getLogger(CupomService.class);

    private final CupomRepository cupomRepository;


    public CupomService(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    @Override
    public Cupom criar(Cupom cupom) {
        if (cupomRepository.findByCodigoAndAtivoTrue(cupom.getCodigo()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um cupom ativo com esse código.");
        }
        Cupom salvo = cupomRepository.save(cupom);
        logger.info("Cupom criado: {}", salvo.getCodigo());
        return salvo;
    }

    @Override
    public List<Cupom> ListarAtivos() {
        return cupomRepository.findAll().stream()
                .filter(Cupom::isAtivo)
                .toList();
    }

    @Override
    public Cupom buscarPorId(Long id) {
        return cupomRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cupom não encontrado: " + id));
    }

    @Override
    public Cupom validar(String codigo) {
        Cupom cupom = cupomRepository.findByCodigoAndAtivoTrue(codigo)
                .orElseThrow(() -> new RegraDeNegocioException("Cupom inválido ou expirado."));
        if (cupom.getValidade().isBefore(LocalDateTime.now())) {
            throw new RegraDeNegocioException("Cupom expirado.");
        }
        return cupom;
    }

    @Override
    public void desativar(Long id) {
        Cupom cupom = buscarPorId(id);
        cupom.setAtivo(false);
        cupomRepository.save(cupom);
        logger.info("Cupom desativado: {}", cupom.getCodigo());
    }
}
