package deliveryhamburgueriabk.service.interfaces;

import deliveryhamburgueriabk.model.Cupom;
import java.util.List;

public interface ICupomService {
    Cupom criar(Cupom cupom);
    List<Cupom> listarAtivos();
    Cupom buscarPorId(Long id);
    Cupom validar(String codigo);
    void desativar(Long id);
}