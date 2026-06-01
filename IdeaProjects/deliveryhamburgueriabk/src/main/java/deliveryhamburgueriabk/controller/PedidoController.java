package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.service.interfaces.IPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final IPedidoService pedidoService;

    public PedidoController(IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Map<String, Object> body) {

        Long usuarioId        = Long.valueOf(body.get("usuarioId").toString());
        TipoPedido tipoPedido = TipoPedido.valueOf(body.get("tipoPedido").toString());
        FormaPagamento forma  = FormaPagamento.valueOf(body.get("formaPagamento").toString());
        String endereco       = (String) body.getOrDefault("enderecoEntrega", "");
        String cupom          = (String) body.getOrDefault("codigoCupom", "");
        List<Long> produtoIds = ((List<?>) body.get("produtoIds"))
                .stream().map(id -> Long.valueOf(id.toString())).toList();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.criar(usuarioId, tipoPedido, forma, endereco, produtoIds, cupom));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id,
                                                  @RequestParam StatusPedido status) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, status));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> historico(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.historicoPorUsuario(usuarioId));
    }
}