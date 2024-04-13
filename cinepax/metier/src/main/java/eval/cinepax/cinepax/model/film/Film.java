package eval.cinepax.cinepax.model.film;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_film", nullable = false, unique = true)
    int idFilm;
    @Column(name = "nom_film", nullable = false, unique = true)
    String nomFilm;
    public Film() {
    }
    public Film(int idFilm)
    throws Exception {
        this.setIdFilm(idFilm);
    }
    public int getIdFilm() {
        return idFilm;
    }
    public void setIdFilm(int idFilm)
    throws Exception {
        if(idFilm<=0) {
            throw new Exception("Veuillez entrer un id de film valide");
        }
        this.idFilm = idFilm;
    }
    public String getNomFilm() {
        return nomFilm;
    }
    public void setNomFilm(String nomFilm)
    throws Exception {
        if(nomFilm==null||nomFilm.length()==0) {
            throw new Exception("Veuillez entrer un nom de film");
        }
        this.nomFilm = nomFilm;
    }
}
