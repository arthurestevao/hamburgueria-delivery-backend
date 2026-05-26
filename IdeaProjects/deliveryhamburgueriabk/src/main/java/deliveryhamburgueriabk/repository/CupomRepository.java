package deliveryhamburgueriabk.repository;

import deliveryhamburgueriabk.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
    Optional<Cupom> findByCodigoAndAtivoTrue(String codigo);
}