package eval.mikolo.mikolo.model.laptop.stat;

import eval.mikolo.mikolo.model.Constante;

public class StatBeneficeMois {
    double beneficeTotal;
    int num_mois;
    int annee;
    String mois;
    public StatBeneficeMois(double beneficeTotal, int num_mois, int annee) {
        this.setBeneficeTotal(beneficeTotal);
        this.setNum_mois(num_mois);
        this.setAnnee(annee);
    }
    public int getNum_mois() {
        return num_mois;
    }
    public void setNum_mois(int num_mois) {
        this.setMois(Constante.getListeMois()[num_mois-1]);
        this.num_mois = num_mois;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public String getMois() {
        return mois;
    }
    public void setMois(String mois) {
        this.mois = mois;
    }
    public double getBeneficeTotal() {
        return beneficeTotal;
    }
    public void setBeneficeTotal(double beneficeTotal) {
        this.beneficeTotal = beneficeTotal;
    }
}
