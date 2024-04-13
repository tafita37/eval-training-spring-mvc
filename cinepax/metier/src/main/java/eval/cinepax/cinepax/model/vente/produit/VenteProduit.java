package eval.cinepax.cinepax.model.vente.produit;

import java.sql.Date;
import eval.cinepax.cinepax.model.film.produit.Produit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vente_produit")
public class VenteProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vente_produit")
    int idVenteProduit;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;
    @Column(name = "date_vente_produit")
    Date dateVenteProduit;
    @Column(name = "quantite_vendu")
    int quantiteVendu;
    @Column(name = "prix_achat")
    double prixAchat;
    @Column(name = "prix_vente")
    double prixVente;
    public VenteProduit(Produit produit, Date dateVenteProduit, int quantiteVendu)
    throws Exception {
        this.setProduit(produit);
        this.setDateVenteProduit(dateVenteProduit);
        this.setQuantiteVendu(quantiteVendu);
        this.setPrixAchat(this.getProduit().getPrixAchat());
        this.setPrixVente(this.getProduit().getPrixVente());
    }
    public VenteProduit() {
    }
    public int getIdVenteProduit() {
        return idVenteProduit;
    }
    public void setIdVenteProduit(int idVenteProduit)
    throws Exception {
        if(idVenteProduit<=0) {
            throw new Exception("Veuillez entrer un id de vente de produit valide");
        }
        this.idVenteProduit = idVenteProduit;
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
    public Date getDateVenteProduit() {
        return dateVenteProduit;
    }
    public void setDateVenteProduit(Date dateVenteProduit)
    throws Exception {
        if(dateVenteProduit==null) {
            throw new Exception("Veuillez entrer un date de vente de produit");
        }
        this.dateVenteProduit = dateVenteProduit;
    }
    public int getQuantiteVendu() {
        return quantiteVendu;
    }
    public void setQuantiteVendu(int quantiteVendu)
    throws Exception {
        if(quantiteVendu<0) {
            throw new Exception("Veuillez entrer une quantite de vente plus grande");
        }
        this.quantiteVendu = quantiteVendu;
    }
    public double getPrixAchat() {
        return prixAchat;
    }
    public void setPrixAchat(double prixAchat)
    throws Exception {
        if(prixAchat<=0) {
            throw new Exception("Veuillez entrer un prix d'achat plus grand");
        }
        this.prixAchat = prixAchat;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente)
    throws Exception {
        if(prixVente<=0) {
            throw new Exception("Veuillez entrer un prix de vente plus grand");
        }
        this.prixVente = prixVente;
    }
}
