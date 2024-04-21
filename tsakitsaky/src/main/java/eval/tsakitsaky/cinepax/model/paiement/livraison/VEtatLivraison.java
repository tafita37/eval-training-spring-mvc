package eval.tsakitsaky.cinepax.model.paiement.livraison;

import java.sql.Date;

import eval.tsakitsaky.cinepax.model.axe.Axe;
import eval.tsakitsaky.cinepax.model.axe.Client;
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
@Table(name = "v_etat_livraison")
public class VEtatLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int idVEtatLivraison;
    @ManyToOne
    @JoinColumn(name = "id_client")
    Client client;
    @ManyToOne
    @JoinColumn(name = "id_axe")
    Axe axe;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;
    @Column(name = "nb_pack")
    double nbPack;
    @Column(name = "date_vente_billet")
    Date dateVenteBillet;
    @Column(name = "montant_total")
    double montantTotal;
    public VEtatLivraison() {
    }
    public int getIdVEtatLivraison() {
        return idVEtatLivraison;
    }
    public void setIdVEtatLivraison(int idVEtatLivraison) {
        this.idVEtatLivraison = idVEtatLivraison;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Axe getAxe() {
        return axe;
    }
    public void setAxe(Axe axe) {
        this.axe = axe;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    public double getNbPack() {
        return nbPack;
    }
    public void setNbPack(double nbPack) {
        this.nbPack = nbPack;
    }
    public double getMontantTotal() {
        return montantTotal;
    }
    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
    public Date getDateVenteBillet() {
        return dateVenteBillet;
    }
    public void setDateVenteBillet(Date dateVenteBillet) {
        this.dateVenteBillet = dateVenteBillet;
    }
}
