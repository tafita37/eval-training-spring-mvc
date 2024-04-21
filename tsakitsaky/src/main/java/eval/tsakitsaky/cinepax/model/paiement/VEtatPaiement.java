package eval.tsakitsaky.cinepax.model.paiement;

import eval.tsakitsaky.cinepax.model.vente.VenteBillet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_etat_paiement")
public class VEtatPaiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name = "id_vente_billet")   
    VenteBillet venteBillet;
    @Column(name = "montant_payer")
    double montantPayer;
    public VEtatPaiement() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public VenteBillet getVenteBillet() {
        return venteBillet;
    }
    public void setVenteBillet(VenteBillet venteBillet) {
        this.venteBillet = venteBillet;
    }
    public double getMontantPayer() {
        return montantPayer;
    }
    public void setMontantPayer(double montantPayer) {
        this.montantPayer = montantPayer;
    }

    public double getResteAPayer() {
        return this.getVenteBillet().getPrixVente()-this.getMontantPayer();
    }
}
