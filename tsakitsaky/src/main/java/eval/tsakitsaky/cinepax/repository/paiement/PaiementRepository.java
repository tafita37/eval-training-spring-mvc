package eval.tsakitsaky.cinepax.repository.paiement;

import org.springframework.data.jpa.repository.JpaRepository;

import eval.tsakitsaky.cinepax.model.paiement.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
    
}
