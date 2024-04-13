package eval.cinepax.cinepax.model.film.produit;

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
    @Column(name = "id_produit", unique = true, nullable = false)
    int idProduit;
    @Column(name = "nom_produit", unique = true, nullable = false)
    String nomProduit;
    @Column(name = "prix_achat", nullable = false)
    double prixAchat;
    @Column(name = "prix_vente", nullable = false)
    double prixVente;
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
            throw new Exception("Veuillez entrer un produit");
        }
        this.nomProduit = nomProduit;
    }
    public double getPrixAchat() {
        return prixAchat;
    }
    public void setPrixAchat(double prixAchat)
    throws Exception {
        if(prixAchat<0) {
            throw new Exception("Veuillez entrer un prix d'achat plus grand");
        }
        this.prixAchat = prixAchat;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente)
    throws Exception {
        if(prixVente<0) {
            throw new Exception("Veuillez entrer un prix de vente plus grand");
        }
        this.prixVente = prixVente;
    }
}
