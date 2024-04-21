package eval.tsakitsaky.cinepax.model.axe;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "axe")
public class Axe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_axe")
    int idAxe;
    @Column(name = "nom_axe")
    String nomAxe;
    @OneToMany(mappedBy = "idAxe")
    List<Quartier> listeQuartier;
    public Axe() {
    }
    public int getIdAxe() {
        return idAxe;
    }
    public void setIdAxe(int idAxe) {
        this.idAxe = idAxe;
    }
    public String getNomAxe() {
        return nomAxe;
    }
    public void setNomAxe(String nomAxe)
    throws Exception {
        if(nomAxe==null||nomAxe.length()==0) {
            throw new Exception("Veuillez entrer un nom d'axe");
        }
        this.nomAxe = nomAxe;
    }
    public List<Quartier> getListeQuartier() {
        return listeQuartier;
    }
    public void setListeQuartier(List<Quartier> listeQuartier)
    throws Exception {
        if(listeQuartier==null||listeQuartier.size()==0) {
            throw new Exception("Veuillez entrer une liste de quartier");
        }
        this.listeQuartier = listeQuartier;
    }

    public String getQuartierConcatener() {
        return this.getListeQuartier().stream().map(Quartier::getNomQuartier).collect(Collectors.joining(", "));
    }

    public String getNomAfficher() {
        return this.getNomAxe()+
                " : "+
                this.getListeQuartier().stream().map(Quartier::getNomQuartier).collect(Collectors.joining(", "));
    }
}
