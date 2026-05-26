package deliveryhamburgueriabk.service;
import deliveryhamburgueriabk.exception.RecursoNaoEncontradoException;
import deliveryhamburgueriabk.exception.RegraDeNegocioException;
import deliveryhamburgueriabk.model.Usuario;
import deliveryhamburgueriabk.repository.UsuarioRepository;
import deliveryhamburgueriabk.service.interfaces.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UsuarioService implements IUsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RegraDeNegocioException("E-mail já cadastrado.");
        }
        Usuario salvo = usuarioRepository.save(usuario);
        logger.info("Usuário cadastrado com sucesso: {}", salvo.getEmail());
        return salvo;
    }

    @Override
    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
        if (!usuario.getSenha().equals(senha)) {
            throw new RegraDeNegocioException("Senha incorreta.");
        }
        logger.info("Login realizado: {}", email);
        return usuario;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado: " + id));
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}