package eval.cinepax.cinepax.repository.film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eval.cinepax.cinepax.model.film.Film;
import jakarta.transaction.Transactional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into film(nom_film) select film from tmp_table group by film", nativeQuery = true)
    public int saveFilmExcel();
}
