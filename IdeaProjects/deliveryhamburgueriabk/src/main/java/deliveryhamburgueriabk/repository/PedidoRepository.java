package deliveryhamburgueriabk.repository;

import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioId(Long usuarioId);
    List<Pedido> findByStatusPedido(StatusPedido status);
}