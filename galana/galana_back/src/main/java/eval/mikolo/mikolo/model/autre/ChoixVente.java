package eval.mikolo.mikolo.model.autre;

import java.sql.Date;
import java.util.HashMap;

import eval.mikolo.mikolo.model.produit.Produit;

public class ChoixVente {
    Choix choix;
    double quantite;
    Produit produit;
    Date date;

    public HashMap<Integer, Double> getChoixPossible() {
        HashMap<Integer, Double> result=new HashMap<>();
        result.put(1, this.getQuantite()/this.getProduit().getPrixVente());
        result.put(2, this.getQuantite());
        return result;
    }

    public double getRealQuantite() {
        return this.getChoixPossible().get(choix.getIdChoix());
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Choix getChoix() {
        return choix;
    }

    public void setChoix(Choix choix) {
        this.choix = choix;
    }
}
