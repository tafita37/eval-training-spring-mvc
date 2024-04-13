package eval.cinepax.cinepax.model.film.produit;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_chiffre_affaire_produit_total")
public class VChiffreAffaireBProduitJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;
    @Column(name = "date_vente_produit")
    Date dateVenteProduit;
    @Column(name = "ca_produit")
    double caProduit;
    @Column(name = "pourcentage")
    double pourcentage;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Date getDateVenteProduit() {
        return dateVenteProduit;
    }
    public void setDateVenteProduit(Date dateVenteProduit) {
        this.dateVenteProduit = dateVenteProduit;
    }
    public double getCaProduit() {
        return caProduit;
    }
    public void setCaProduit(double caProduit) {
        this.caProduit = caProduit;
    }
    public double getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
}
