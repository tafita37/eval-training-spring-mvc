package eval.cinepax.cinepax.model.place;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "v_count_nb_place_b_rangee")
public class VCountPlaceRangee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    int id;
    @Column(name = "nb_place")
    int nbPlace;
    @ManyToOne
    @JoinColumn(name = "id_rangee", referencedColumnName = "id_rangee")
    Rangee rangee;
    @Transient
    List<VPlaceEtat> listeVPlaceEtat;

    public VCountPlaceRangee() {
    }
    public VCountPlaceRangee(int nbPlace, Rangee rangee) {
        this.nbPlace = nbPlace;
        this.rangee = rangee;
    }
    public int getNbPlace() {
        return nbPlace;
    }
    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
    public Rangee getRangee() {
        return rangee;
    }
    public void setRangee(Rangee rangee) {
        this.rangee = rangee;
    }
    public List<VPlaceEtat> getListeVPlaceEtat() {
        return listeVPlaceEtat;
    }
    public void setListeVPlaceEtat(List<VPlaceEtat> listeVPlaceEtat) {
        this.listeVPlaceEtat = listeVPlaceEtat;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
