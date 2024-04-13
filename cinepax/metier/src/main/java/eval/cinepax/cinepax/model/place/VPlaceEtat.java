package eval.cinepax.cinepax.model.place;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_place_etat")
public class VPlaceEtat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place", unique = true, nullable = false)
    int idPlace;
    @Column(name = "numero_place", nullable = false)
    int numeroPlace;
    @JoinColumn(name = "id_rangee", unique = true, nullable = false)
    @ManyToOne
    Rangee rangee;
    @Column(name = "occupee", nullable = false)
    boolean occupee;
    public VPlaceEtat() {
    }
    public VPlaceEtat(int idPlace)
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
        return this.getRangee().getNomRangee()+this.getNumeroPlace();
    }

    public String getButtonClass() {
        if(this.isOccupee()) {
            return "btn btn-danger btn-icon-text";
        }
        return "btn btn-primary btn-icon-text";
    }
    public boolean isOccupee() {
        return occupee;
    }
    public void setOccupee(boolean occupee) {
        this.occupee = occupee;
    }
}
