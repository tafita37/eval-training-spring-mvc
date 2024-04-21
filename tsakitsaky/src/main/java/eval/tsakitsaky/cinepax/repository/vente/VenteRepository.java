package eval.tsakitsaky.cinepax.repository.vente;

import org.springframework.data.jpa.repository.JpaRepository;

import eval.tsakitsaky.cinepax.model.vente.VenteBillet;
import java.util.List;


public interface VenteRepository extends JpaRepository<VenteBillet, Integer> {
    List<VenteBillet> findByEstPaye(int estPaye);
}
