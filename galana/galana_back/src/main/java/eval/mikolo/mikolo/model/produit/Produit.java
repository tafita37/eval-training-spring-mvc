package eval.mikolo.mikolo.model.produit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "prix_revient")
    double prixRevient;
    @Column(name = "prix_vente")
    double prixVente;
    @Column(name = "pourcentage_evaporation_j")
    double pourcentageEvaporationJ;
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit)
    throws Exception {
        if(idProduit<=0) {
            throw new Exception("Veuillez entrer un id de produit valide");
        }
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
    public double getPrixRevient() {
        return prixRevient;
    }
    public void setPrixRevient(double prixRevient)
    throws Exception {
        if(prixRevient<=0) {
            throw new Exception("Veuillez entrer un prix de revient plus grand");
        }
        this.prixRevient = prixRevient;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente)
    throws Exception {
        if(prixVente<=0) {
            throw new Exception("Veuillez entrer un prix d'achat plus grand");
        }
        this.prixVente = prixVente;
    }
    public double getPourcentageEvaporationJ() {
        return pourcentageEvaporationJ;
    }
    public void setPourcentageEvaporationJ(double pourcentageEvaporationJ)
    throws Exception {
        if(pourcentageEvaporationJ<0||pourcentageEvaporationJ>100) {
            throw new Exception("Veuillez entrer un pourcentage d'evaporation compris entre 0 et 100");
        }
        this.pourcentageEvaporationJ = pourcentageEvaporationJ;
    }
}
