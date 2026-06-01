package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.service.interfaces.IEntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

    private final IEntregaService entregaService;

    public EntregaController(IEntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PatchMapping("/{pedidoId}/sair")
    public ResponseEntity<Pedido> sairParaEntrega(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(entregaService.sairParaEntrega(pedidoId));
    }

    @PatchMapping("/{pedidoId}/confirmar")
    public ResponseEntity<Pedido> confirmarEntrega(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(entregaService.confirmarEntrega(pedidoId));
    }
}