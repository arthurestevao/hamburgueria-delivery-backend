package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.enums.Perfil;
import deliveryhamburgueriabk.model.Entregador;
import deliveryhamburgueriabk.model.Usuario;
import deliveryhamburgueriabk.service.interfaces.IEntregadorService;
import deliveryhamburgueriabk.service.interfaces.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final IUsuarioService usuarioService;
    private final IEntregadorService entregadorService;

    public AdminController(IUsuarioService usuarioService, IEntregadorService entregadorService) {
        this.usuarioService = usuarioService;
        this.entregadorService = entregadorService;
    }

    @PostMapping("/entregadores")
    public ResponseEntity<Entregador> cadastrarEntregador(
            @RequestBody Map<String, String> dados) {

        Entregador entregador = entregadorService.cadastrar(
                dados.get("nome"),
                dados.get("email"),
                dados.get("senha"),
                dados.get("telefone"),
                dados.get("endereco"),
                dados.get("veiculo")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(entregador);
    }

    @GetMapping("/entregadores")
    public ResponseEntity<List<Entregador>> listarEntregadores() {
        return ResponseEntity.ok(entregadorService.listarTodos());
    }

    @DeleteMapping("/entregadores/{id}")
    public ResponseEntity<Void> removerEntregador(@PathVariable Long id) {
        entregadorService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Usuario>> listarClientes() {
        return ResponseEntity.ok(usuarioService.listarPorPerfil(Perfil.CLIENTE));
    }
}