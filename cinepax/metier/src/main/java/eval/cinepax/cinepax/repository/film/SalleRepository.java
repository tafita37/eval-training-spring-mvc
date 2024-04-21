package eval.cinepax.cinepax.repository.film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eval.cinepax.cinepax.model.film.Salle;
import jakarta.transaction.Transactional;

public interface SalleRepository extends JpaRepository<Salle, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into salle(nom_salle) select salle from tmp_table group by salle", nativeQuery = true)
    public int insertSalleExcel();
}
