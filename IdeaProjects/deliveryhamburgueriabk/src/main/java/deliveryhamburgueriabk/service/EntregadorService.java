package deliveryhamburgueriabk.service;

import deliveryhamburgueriabk.enums.Perfil;
import deliveryhamburgueriabk.exception.RegraDeNegocioException;
import deliveryhamburgueriabk.exception.RecursoNaoEncontradoException;
import deliveryhamburgueriabk.model.Entregador;
import deliveryhamburgueriabk.model.Usuario;
import deliveryhamburgueriabk.repository.EntregadorRepository;
import deliveryhamburgueriabk.repository.UsuarioRepository;
import deliveryhamburgueriabk.service.interfaces.IEntregadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EntregadorService implements IEntregadorService {

    static final Logger logger = LoggerFactory.getLogger(EntregadorService.class);
    private final EntregadorRepository entregadorRepository;
    private final UsuarioRepository usuarioRepository;

    public EntregadorService(EntregadorRepository entregadorRepository, UsuarioRepository usuarioRepository) {
        this.entregadorRepository = entregadorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Entregador cadastrar(String nome, String email, String senha, String telefone, String endereco, String veiculo){

        if (usuarioRepository.existsByEmail(email)) {
            throw new RegraDeNegocioException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTelefone(telefone);
        usuario.setEndereco(endereco);
        usuario.setPerfil(Perfil.ENTREGADOR);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        Entregador entregador = new Entregador(usuarioSalvo, veiculo);
        Entregador salvo = entregadorRepository.save(entregador);
        logger.info("Entregador cadastrado: {} (usuarioId={})", usuarioSalvo.getEmail(), usuarioSalvo.getId());

        return salvo;
    }

    @Override
    public List<Entregador> listarTodos() {
        return entregadorRepository.findAll();
    }

    @Override
    public Entregador buscarPorId(Long id) {
        return entregadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Entregador não encontrado: " + id));
    }

    @Override
    public void remover(Long id) {
        Entregador entregador = buscarPorId(id);
        entregadorRepository.delete(entregador);
        logger.info("Entregador removido: id={}", id);
    }
}