package eval.cinepax.cinepax.view.stat;

import eval.cinepax.cinepax.model.film.Film;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_stat_vente_billet_b_film")
public class StatVenteBFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    int id;
    @Column(name = "nbVente", unique = true, nullable = false)
    int nbVente;
    @ManyToOne
    @JoinColumn(name = "id_film")
    Film film;
    @Column(name = "pourcentage_vente")
    double pourcentageVente; 
    public int getId() {
        return id;
    }
    public void setId(int id)
    throws Exception {
        if(id<=0) {
            throw new Exception("Veuillez entrer une id de statistique de vente de film valide");
        }
        this.id = id;
    }
    public int getNbVente() {
        return nbVente;
    }
    public void setNbVente(int nbVente)
    throws Exception {
        if(nbVente<0) {
            throw new Exception("Veuillez entrer un nombre de vente plus grand");
        }
        this.nbVente = nbVente;
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film)
    throws Exception {
        if(film==null) {
            throw new Exception("Veuillez entrer unfilm");
        }
        this.film = film;
    }
    public double getPourcentageVente() {
        return pourcentageVente;
    }
    public void setPourcentageVente(double pourcentageVente)
    throws Exception {
        if(pourcentageVente<0||pourcentageVente>100) {
            throw new Exception("Pourcentage invalide");
        }
        this.pourcentageVente = pourcentageVente;
    }
}
