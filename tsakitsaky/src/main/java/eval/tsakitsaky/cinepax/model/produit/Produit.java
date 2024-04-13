package eval.tsakitsaky.cinepax.model.produit;

import eval.tsakitsaky.cinepax.model.produit.unite.Unite;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    int idProduit;
    @Column(name = "nom_produit")
    String nomProduit;
    @ManyToOne
    @JoinColumn(name = "id_unite")
    Unite unite;
    @Column(name = "cout_revient_unitaire")
    double coutRevientUnitaire;
    @Column(name = "nb_unitaire")
    double nbUnitaire;
    public Produit(int idProduit) {
        this.setIdProduit(idProduit);
    }
    public Produit() {
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public String getNomProduit() {
        return nomProduit;
    }
    public void setNomProduit(String nomProduit)
    throws Exception {
        if(nomProduit==null||nomProduit.length()==0) {
            throw new Exception("Veuillez entrer un nom de produit");
        }
        this.nomProduit = nomProduit;
    }
    public Unite getUnite() {
        return unite;
    }
    public void setUnite(Unite unite)
    throws Exception {
        if(unite==null) {
            throw new Exception("Veuillez entrer une unite");
        }
        this.unite = unite;
    }
    public double getCoutRevientUnitaire() {
        return coutRevientUnitaire;
    }
    public void setCoutRevientUnitaire(double coutRevientUnitaire)
    throws Exception {
        if(coutRevientUnitaire<=0) {
            throw new Exception("Veuillez entrer un cout de revient unitaire plus grand");
        }
        this.coutRevientUnitaire = coutRevientUnitaire;
    }
    public double getNbUnitaire() {
        return nbUnitaire;
    }
    public void setNbUnitaire(double nbUnitaire)
    throws Exception {
        if(nbUnitaire<=0) {
            throw new Exception("Veuillez entrer un nombre unitaire plus grand");
        }
        this.nbUnitaire = nbUnitaire;
    }

    public double getCoutRevientBUnite() {
        return coutRevientUnitaire/nbUnitaire;
    }
}
