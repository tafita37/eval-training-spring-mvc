package eval.tsakitsaky.cinepax.model.produit.pack;

import eval.tsakitsaky.cinepax.model.produit.Produit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pack_formule")
public class PackFormule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack_formule")
    int idPackFormule;
    @Column(name = "id_pack")
    int idPack;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;
    @Column(name = "quantite_produit")
    double quantiteProduit;
    public PackFormule(int idPack, Produit produit, double quantiteProduit)
    throws Exception {
        this.setIdPack(idPack);
        this.setProduit(produit);
        this.setQuantiteProduit(quantiteProduit);
    }
    public PackFormule() {
    }
    public int getIdPackFormule() {
        return idPackFormule;
    }
    public void setIdPackFormule(int idPackFormule) {
        this.idPackFormule = idPackFormule;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit)
    throws Exception {
        if(produit==null) {
            throw new Exception("Veuillez entrer un produit");
        }
        this.produit = produit;
    }
    public double getQuantiteProduit() {
        return quantiteProduit;
    }
    public void setQuantiteProduit(double quantiteProduit)
    throws Exception {
        if(quantiteProduit<=0) {
            throw new Exception("Veuillez entrer une quantite de produit plus grande");
        }
        this.quantiteProduit = quantiteProduit;
    }
    public int getIdPack() {
        return idPack;
    }
    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }
}
