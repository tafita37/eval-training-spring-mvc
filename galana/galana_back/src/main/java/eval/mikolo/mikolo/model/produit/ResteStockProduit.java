package eval.mikolo.mikolo.model.produit;

import eval.mikolo.mikolo.model.profile.Station;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ResteStockProduit {
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;
    @ManyToOne
    @JoinColumn(name = "id_station")
    Station station;
    @Column(name = "reste_stock")
    double resteStock;
    public int getId() {
        return id;
    }
    public void setId(int id)
    throws Exception {
        if(id<=0) {
            throw new Exception("Veuillez entrer un id de reste de stock de produit valide");
        }
        this.id = id;
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
    public Station getStation() {
        return station;
    }
    public void setStation(Station station)
    throws Exception {
        if(station==null) {
            throw new Exception("Veuillez entrer un station");
        }
        this.station = station;
    }
    public double getResteStock() {
        return resteStock;
    }
    public void setResteStock(double resteStock)
    throws Exception {
        if(resteStock<0) {
            throw new Exception("Veuillez entrer un rest de stock plus grand");
        }
        this.resteStock = resteStock;
    }
}
