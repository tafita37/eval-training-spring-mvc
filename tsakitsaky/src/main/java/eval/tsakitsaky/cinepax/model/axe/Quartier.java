package eval.tsakitsaky.cinepax.model.axe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quartier")
public class Quartier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quartier")
    int idQuartier;
    @Column(name = "nom_quartier")
    String nomQuartier;
    @Column(name = "id_axe")
    int idAxe;

    public Quartier() {
    }
    public int getIdQuartier() {
        return idQuartier;
    }
    public void setIdQuartier(int idQuartier) {
        this.idQuartier = idQuartier;
    }
    public String getNomQuartier() {
        return nomQuartier;
    }
    public void setNomQuartier(String nomQuartier)
    throws Exception {
        if(nomQuartier==null||nomQuartier.length()==0) {
            throw new Exception("Veuillez entrer un nom de quartier");
        }
        this.nomQuartier = nomQuartier;
    }
    public int getIdAxe() {
        return idAxe;
    }
    public void setIdAxe(int idAxe) {
        this.idAxe = idAxe;
    }
}
