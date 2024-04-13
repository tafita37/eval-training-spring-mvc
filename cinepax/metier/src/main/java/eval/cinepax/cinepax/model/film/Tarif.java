package eval.cinepax.cinepax.model.film;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarif")
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarif")
    int idTarif;
    @Column(name = "debut_horaire")
    Time debutHoraire;
    @Column(name = "prix_tarif")
    double prixTarif;
    @Column(name = "categorie_age")
    int categorieAge;
    public int getIdTarif() {
        return idTarif;
    }
    public void setIdTarif(int idTarif)
    throws Exception {
        if(idTarif<=0) {
            throw new Exception("Veuillez entrer un id de tarif valide");
        }
        this.idTarif = idTarif;
    }
    public Time getDebutHoraire() {
        return debutHoraire;
    }
    public void setDebutHoraire(Time debutHoraire)
    throws Exception {
        if(debutHoraire==null) {
            throw new Exception("Veuillez entrer un debut de taux horaire");
        }
        this.debutHoraire = debutHoraire;
    }
    public double getPrixTarif() {
        return prixTarif;
    }
    public void setPrixTarif(double prixTarif)
    throws Exception {
        if(prixTarif<=0) {
            throw new Exception("Veuillez entrer un prix de tarif plus grand");
        }
        this.prixTarif = prixTarif;
    }
    public int getCategorieAge() {
        return categorieAge;
    }
    public void setCategorieAge(int categorieAge)
    throws Exception {
        if(categorieAge!=0&&categorieAge!=11) {
            throw new Exception("Categorie d'age invalide");
        }
        this.categorieAge = categorieAge;
    }

    public String getCategorieName() {
        if(categorieAge==0) {
            return "Adulte";
        } else {
            return "Enfant";
        }
    }
}
