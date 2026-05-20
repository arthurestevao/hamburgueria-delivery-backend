package deliveryhamburgueriabk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    @GetMapping("/teste")
    public String teste(){

        return "Backend funcionando!";
    }
}
