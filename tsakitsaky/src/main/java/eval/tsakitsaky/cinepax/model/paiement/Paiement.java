package eval.tsakitsaky.cinepax.model.paiement;

import java.sql.Date;
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
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    int idPaiement;
    @ManyToOne
    @JoinColumn(name = "id_vente_billet")
    VenteBillet venteBillet;
    @Column(name = "montant_payer")
    double montantPayer;
    @Column(name = "date_paiement")
    Date datePaiement;
    public Paiement(VenteBillet venteBillet, double montantPayer, Date datePaiement)
    throws Exception {
        this.setVenteBillet(venteBillet);
        this.setMontantPayer(montantPayer);
        this.setDatePaiement(datePaiement);
    }
    public Paiement() {
    }
    public int getIdPaiement() {
        return idPaiement;
    }
    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }
    public VenteBillet getVenteBillet() {
        return venteBillet;
    }
    public void setVenteBillet(VenteBillet venteBillet)
    throws Exception {
        if(venteBillet==null) {
            throw new Exception("Veuillez entrer une vente de billet");
        }
        if(this.getDatePaiement()!=null&&venteBillet.getDateVenteBillet()!=null) {
            if(this.getDatePaiement().compareTo(venteBillet.getDateVenteBillet())<0) {
                throw new Exception("La date de paiement doit etre apres la date de vente du billet");
            }
        }
        this.venteBillet = venteBillet;
    }
    public double getMontantPayer() {
        return montantPayer;
    }
    public void setMontantPayer(double montantPayer)
    throws Exception {
        if(montantPayer<0) {
            throw new Exception("Veuillez entrer un montant a payer plus grand");
        }
        this.montantPayer = montantPayer;
    }
    public Date getDatePaiement() {
        return datePaiement;
    }
    public void setDatePaiement(Date datePaiement)
    throws Exception {
        if(datePaiement==null) {
            throw new Exception("Veuillez entrer une date de paiement");
        }
        if(this.getVenteBillet().getDateVenteBillet()!=null) {
            if(datePaiement.compareTo(this.getVenteBillet().getDateVenteBillet())<0) {
                throw new Exception("La date de paiement doit etre apres la date de vente du billet");
            }
        }
        this.datePaiement = datePaiement;
    }
}
