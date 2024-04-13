package eval.tsakitsaky.cinepax.repository.stat;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.stat.VStatVenteEtudiant;
import java.util.Optional;
import eval.tsakitsaky.cinepax.model.user.Etudiant;


public interface VStatVenteEtudiantRepository extends JpaRepository<VStatVenteEtudiant, Integer> {
    Optional<VStatVenteEtudiant> findByEtudiant(Etudiant etudiant);
}
