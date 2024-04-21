package eval.tsakitsaky.cinepax.model.paiement;

import eval.tsakitsaky.cinepax.model.user.Etudiant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_etat_paiement_b_etudiant")
public class VEtatPaiementBEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;  
    @Column(name = "montant_payer")
    double montantPayer;
    @Column(name = "reste_payer")
    double restePayer;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    public double getMontantPayer() {
        return montantPayer;
    }
    public void setMontantPayer(double montantPayer) {
        this.montantPayer = montantPayer;
    }
    public double getRestePayer() {
        return restePayer;
    }
    public void setRestePayer(double restePayer) {
        this.restePayer = restePayer;
    }
}
