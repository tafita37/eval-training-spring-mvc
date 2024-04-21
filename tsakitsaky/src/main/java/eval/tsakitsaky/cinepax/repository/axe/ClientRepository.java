package eval.tsakitsaky.cinepax.repository.axe;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.axe.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
