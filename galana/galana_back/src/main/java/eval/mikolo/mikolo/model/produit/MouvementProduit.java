package eval.mikolo.mikolo.model.produit;

import java.sql.Date;
import java.util.HashMap;
import eval.mikolo.mikolo.model.autre.ChoixVente;
import eval.mikolo.mikolo.model.profile.Station;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mouvement_produit")
public class MouvementProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mouvement_produit")
    int idMouvementProduit;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;
    @ManyToOne
    @JoinColumn(name = "id_station")
    Station station;
    @Column(name = "entree_produit")
    double entreeProduit;
    @Column(name = "sortie_produit")
    double sortieProduit;
    @Column(name = "date_mouvement_produit")
    Date dateMouvementProduit;
    @Column(name = "prix_revient")
    double prixRevient;
    @Column(name = "prix_vente")
    double prixVente;

    public MouvementProduit() {
    }
    public MouvementProduit(
        Produit produit, 
        Station station, 
        double entreeProduit, 
        double sortieProduit, 
        Date dateMouvementProduit, 
        double prixRevient, 
        double prixVente
    )
    throws Exception {
        this.setProduit(produit);
        this.setStation(station);
        this.setEntreeProduit(entreeProduit);
        this.setSortieProduit(sortieProduit);
        this.setDateMouvementProduit(dateMouvementProduit);
        this.setPrixRevient(prixRevient);
        this.setPrixVente(prixVente);
    }
    public int getIdMouvementProduit() {
        return idMouvementProduit;
    }
    public void setIdMouvementProduit(int idMouvementProduit)
    throws Exception {
        if(idMouvementProduit<=0) {
            throw new Exception("Veuillez entrer un id de mouvement de produit valide");
        }
        this.idMouvementProduit = idMouvementProduit;
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
    public double getEntreeProduit() {
        return entreeProduit;
    }
    public void setEntreeProduit(double entreeProduit)
    throws Exception {
        if(entreeProduit<0) {
            throw new Exception("Veuillez entrer une entree de produit plus grande");
        }
        this.entreeProduit = entreeProduit;
    }
    public double getSortieProduit() {
        return sortieProduit;
    }
    public void setSortieProduit(double sortieProduit)
    throws Exception {
        if(sortieProduit<0) {
            throw new Exception("Veuillez entrer une sortie de produit plus grande");
        }
        this.sortieProduit = sortieProduit;
    }
    public Date getDateMouvementProduit() {
        return dateMouvementProduit;
    }
    public void setDateMouvementProduit(Date dateMouvementProduit)
    throws Exception {
        if(dateMouvementProduit==null) {
            throw new Exception("Veuillez entrer une date de mouvement de produit");
        }
        this.dateMouvementProduit = dateMouvementProduit;
    }
    public double getPrixRevient() {
        return prixRevient;
    }
    public void setPrixRevient(double prixRevient)
    throws Exception {
        if(prixRevient<0) {
            throw new Exception("Veuillez entrer un prix de revient plus grand");
        }
        this.prixRevient = prixRevient;
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
    public Station getStation() {
        return station;
    }
    public void setStation(Station station)
    throws Exception {
        if(station==null) {
            throw new Exception("Veuillez entrer une station");
        }
        this.station = station;
    }
}
