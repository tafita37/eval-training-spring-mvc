package eval.cinepax.cinepax.model.place;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place", unique = true, nullable = false)
    int idPlace;
    @Column(name = "numero_place", nullable = false)
    int numeroPlace;
    @JoinColumn(name = "id_rangee", unique = true, nullable = false)
    @ManyToOne
    Rangee rangee;
    public Place() {
    }
    public Place(int idPlace)
    throws Exception {
        this.setIdPlace(idPlace);
    }
    public int getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int idPlace)
    throws Exception {
        if(idPlace<=0) {
            throw new Exception("Veuillez entrer une id de place valide");
        }
        this.idPlace = idPlace;
    }
    public int getNumeroPlace() {
        return numeroPlace;
    }
    public void setNumeroPlace(int numeroPlace)
    throws Exception {
        if(numeroPlace<=0) {
            throw new Exception("Veuillez entrer une numero de place valide");
        }
        this.numeroPlace = numeroPlace;
    }
    public Rangee getRangee() {
        return rangee;
    }
    public void setRangee(Rangee rangee)
    throws Exception {
        if(rangee==null) {
            throw new Exception("Veuillez entrer une rangee");
        }
        this.rangee = rangee;
    }

    public String getPlaceName() {
        return "Salle : "+this.getRangee().getSalle().getNomSalle()+" "+this.getRangee().getNomRangee()+this.getNumeroPlace();
    }
}
