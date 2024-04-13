package eval.cinepax.cinepax.model.place;

import eval.cinepax.cinepax.model.film.Salle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rangee")
public class Rangee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rangee", nullable = false, unique = true)
    int idRangee;
    @Column(name = "nom_rangee", nullable = false)
    String nomRangee;
    @Column(name = "rang_rangee", nullable = false)
    int rangRangee;
    @ManyToOne
    @JoinColumn(name = "id_salle", nullable = false)
    Salle salle;
    public int getIdRangee() {
        return idRangee;
    }
    public void setIdRangee(int idRangee)
    throws Exception {
        if(idRangee<=0) {
            throw new Exception("Veuillez entrer un id de rangee valide");
        }
        this.idRangee = idRangee;
    }
    public String getNomRangee() {
        return nomRangee;
    }
    public void setNomRangee(String nomRangee)
    throws Exception {
        if(nomRangee==null||nomRangee.length()==0) {
            throw new Exception("Veuillez entrer un nom de rangee");
        }
        this.nomRangee = nomRangee;
    }
    public int getRangRangee() {
        return rangRangee;
    }
    public void setRangRangee(int rangRangee)
    throws Exception {
        if(rangRangee<=0) {
            throw new Exception("Veuillez entrer un rang de rangee valide");
        }
        this.rangRangee = rangRangee;
    }
    public Salle getSalle() {
        return salle;
    }
    public void setSalle(Salle salle)
    throws Exception {
        if(salle==null) {
            throw new Exception("Veuillez entrer une salle");
        }
        this.salle = salle;
    }
}
