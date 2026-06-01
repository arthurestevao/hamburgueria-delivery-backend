package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.model.Cupom;
import deliveryhamburgueriabk.service.interfaces.ICupomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cupons")
@CrossOrigin(origins = "*")

public class CupomController {

    private final ICupomService cupomService;

    public CupomController(ICupomService cupomService) {
        this.cupomService = cupomService;
    }

    @PostMapping
    public ResponseEntity<Cupom> criar(@RequestBody Cupom cupom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cupomService.criar(cupom));
    }

    @GetMapping
    public ResponseEntity<List<Cupom>> listarAtivos(){
        return ResponseEntity.ok(cupomService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cupom> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cupomService.buscarPorId(id));
    }

    @GetMapping("/validar/{codigo}")
    public ResponseEntity<Cupom> validar(@PathVariable String codigo) {
        return ResponseEntity.ok(cupomService.validar(codigo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        cupomService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}