package eval.tsakitsaky.cinepax.repository.paiement.livraison;

import org.springframework.data.jpa.repository.JpaRepository;

import eval.tsakitsaky.cinepax.model.paiement.livraison.VEtatLivraison;

public interface VEtatLivraisonRepository extends JpaRepository<VEtatLivraison, Integer> {
    
}
