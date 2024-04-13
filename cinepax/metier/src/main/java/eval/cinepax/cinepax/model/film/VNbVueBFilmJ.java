package eval.cinepax.cinepax.model.film;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_nb_vue_b_film_j")
public class VNbVueBFilmJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name = "id_film")
    Film film;
    @Column(name = "nb_vue")
    int nbVue;
    @Column(name = "date_vente_billet")
    Date dateVenteBillet;
    @Column(name = "pourcentage")
    int pourcentage;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public int getNbVue() {
        return nbVue;
    }
    public void setNbVue(int nbVue) {
        this.nbVue = nbVue;
    }
    public Date getDateVenteBillet() {
        return dateVenteBillet;
    }
    public void setDateVenteBillet(Date dateVenteBillet) {
        this.dateVenteBillet = dateVenteBillet;
    }
    public int getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }
}
