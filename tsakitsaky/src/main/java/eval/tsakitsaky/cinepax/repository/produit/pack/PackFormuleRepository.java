package eval.tsakitsaky.cinepax.repository.produit.pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eval.tsakitsaky.cinepax.model.produit.pack.PackFormule;
import java.util.List;

public interface PackFormuleRepository extends JpaRepository<PackFormule, Integer> {
    public List<PackFormule> findByIdPack(int idPack);

    @Query(
        value = "select cout_revient_pack from v_pack_cout_revient where id_pack=:idPack", 
        nativeQuery = true
    )
    public double getCoutRevientPack(@Param("idPack") int idPack);
}
