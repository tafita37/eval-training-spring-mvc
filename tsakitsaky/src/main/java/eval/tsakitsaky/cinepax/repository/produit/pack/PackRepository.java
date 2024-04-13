package eval.tsakitsaky.cinepax.repository.produit.pack;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.produit.pack.Pack;
import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Integer> {
    List<Pack> findByDateSuppressionIsNull();
}
