package eval.tsakitsaky.cinepax.model.produit.unite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "unite")
public class Unite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unite")
    int idUnite;
    @Column(name = "nom_unite")
    String nomUnite;
    public Unite() {
    }
    public int getIdUnite() {
        return idUnite;
    }
    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }
    public String getNomUnite() {
        return nomUnite;
    }
    public void setNomUnite(String nomUnite)
    throws Exception {
        if(nomUnite==null||nomUnite.length()==0) {
            throw new Exception("Veuillez entrer un nom d'unite");
        }
        this.nomUnite = nomUnite;
    }
}
