package eval.tsakitsaky.cinepax.repository.paiement;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.paiement.VEtatPaiement;
import java.util.Optional;
import eval.tsakitsaky.cinepax.model.vente.VenteBillet;


public interface VEtatPaiementRepository extends JpaRepository<VEtatPaiement, Integer> {
    public Optional<VEtatPaiement> findByVenteBillet(VenteBillet venteBillet);
}
