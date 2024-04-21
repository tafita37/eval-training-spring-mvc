package eval.tsakitsaky.cinepax.repository.paiement;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.paiement.VEtatPaiementBEtudiant;

public interface VEtatPaiementBEtudiantRepository extends JpaRepository<VEtatPaiementBEtudiant, Integer> {
    
}
