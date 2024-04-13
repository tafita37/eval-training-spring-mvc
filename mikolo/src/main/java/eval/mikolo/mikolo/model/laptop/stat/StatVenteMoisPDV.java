package eval.mikolo.mikolo.model.laptop.stat;

import eval.mikolo.mikolo.model.Constante;

public class StatVenteMoisPDV {
    int quantiteLaptop;
    int num_mois;
    int annee;
    String mois;
    int id_point_vente;
    String nom_point_vente;
    
    public StatVenteMoisPDV(int quantiteLaptop, int num_mois, int annee, int id_point_vente, String nom_point_vente)
    throws Exception {
        this.quantiteLaptop = quantiteLaptop;
        this.setNum_mois(num_mois);
        this.annee = annee;
        this.id_point_vente = id_point_vente;
        this.nom_point_vente = nom_point_vente;
    }
    public int getQuantiteLaptop() {
        return quantiteLaptop;
    }
    public void setQuantiteLaptop(int quantiteLaptop) {
        this.quantiteLaptop = quantiteLaptop;
    }
    public int getNum_mois() {
        return num_mois;
    }
    public void setNum_mois(int num_mois)
    throws Exception {
        if(num_mois<=0||num_mois>12) {
            throw new Exception("Veuillez entrer un numero de mois valide");
        }
        this.setMois(Constante.getListeMois()[num_mois-1]);
        this.num_mois = num_mois;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public String getNom_point_vente() {
        return nom_point_vente;
    }
    public void setNom_point_vente(String nom_point_vente) {
        this.nom_point_vente = nom_point_vente;
    }
    public int getId_point_vente() {
        return id_point_vente;
    }
    public void setId_point_vente(int id_point_vente) {
        this.id_point_vente = id_point_vente;
    }
    public String getMois() {
        return mois;
    }
    public void setMois(String mois) {
        this.mois = mois;
    }
}