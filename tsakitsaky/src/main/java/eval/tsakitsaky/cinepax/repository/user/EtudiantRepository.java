package eval.tsakitsaky.cinepax.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.user.Etudiant;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    public Optional<Etudiant> findByEmailEtudiantAndMdpEtudiant(String emailEtudiant, String mdpEtudiant);
}
