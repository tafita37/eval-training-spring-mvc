package eval.cinepax.cinepax.model.film;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salle")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salle", unique = true, nullable = false)
    int idSalle;
    @Column(name = "nom_salle", unique = true, nullable = false)
    String nomSalle;
    public Salle() {
    }
    public Salle(int idSalle)
    throws Exception {
        this.setIdSalle(idSalle);
    }
    public int getIdSalle() {
        return idSalle;
    }
    public void setIdSalle(int idSalle)
    throws Exception {
        if(idSalle<=0) {
            throw new Exception("Veuillez entrer une id de salle valide");
        }
        this.idSalle = idSalle;
    }
    public String getNomSalle() {
        return nomSalle;
    }
    public void setNomSalle(String nomSalle)
    throws Exception {
        if(nomSalle==null||nomSalle.length()==0) {
            throw new Exception("Veuillez entrer une nom de salle");
        }
        this.nomSalle = nomSalle;
    }
}
