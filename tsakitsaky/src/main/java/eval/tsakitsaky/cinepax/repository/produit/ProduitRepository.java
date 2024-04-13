package eval.tsakitsaky.cinepax.repository.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.produit.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    
}
