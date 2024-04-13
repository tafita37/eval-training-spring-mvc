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
@Table(name = "v_chiffre_affaire_b_film_j")
public class VChiffreAffaireBFilmJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name = "id_film")
    Film film;
    @Column(name = "prix_tarif")
    double prixTarif;
    @Column(name = "pourcentage")
    double pourcentage;
    @Column(name = "date_vente_billet")
    Date dateVenteBillet;
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
    public double getPrixTarif() {
        return prixTarif;
    }
    public void setPrixTarif(double prixTarif) {
        this.prixTarif = prixTarif;
    }
    public double getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    public Date getDateVenteBillet() {
        return dateVenteBillet;
    }
    public void setDateVenteBillet(Date dateVenteBillet) {
        this.dateVenteBillet = dateVenteBillet;
    }
}
