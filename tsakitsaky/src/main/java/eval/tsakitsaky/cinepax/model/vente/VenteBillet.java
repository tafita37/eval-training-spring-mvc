package eval.tsakitsaky.cinepax.model.vente;

import java.sql.Date;

import eval.tsakitsaky.cinepax.model.produit.pack.Pack;
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
@Table(name = "vente_billet")
public class VenteBillet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vente_billet")
    int idVenteBillet;
    @ManyToOne
    @JoinColumn(name = "id_pack")
    Pack pack;
    @Column(name = "quantite_vente_billet")
    double quantiteVenteBillet;
    @Column(name = "date_vente_billet")
    Date dateVenteBillet;
    @Column(name = "cout_revient")
    double coutRevient;
    @Column(name = "prix_vente")
    double prixVente;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;
    @Column(name = "est_paye")
    int estPaye;
    public VenteBillet(Pack pack, double quantiteVenteBillet, Date dateVenteBillet, Etudiant etudiant)
    throws Exception {
        this.setPack(pack);
        this.setQuantiteVenteBillet(quantiteVenteBillet);
        this.setDateVenteBillet(dateVenteBillet);
        this.setEtudiant(etudiant);
        this.setCoutRevient(pack.getCoutRevientPack());
        this.setPrixVente(pack.getPrixVentePack());
    }
    public VenteBillet() {
    }
    public int getIdVenteBillet() {
        return idVenteBillet;
    }
    public void setIdVenteBillet(int idVenteBillet) {
        this.idVenteBillet = idVenteBillet;
    }
    public Pack getPack() {
        return pack;
    }
    public void setPack(Pack pack)
    throws Exception {
        if(pack==null) {
            throw new Exception("Veuillez entrer un pack");
        }
        this.pack = pack;
    }
    public double getQuantiteVenteBillet() {
        return quantiteVenteBillet;
    }
    public void setQuantiteVenteBillet(double quantiteVenteBillet)
    throws Exception {
        if(quantiteVenteBillet<=0) {
            throw new Exception("Veuillez entrer une quantite plus grande a vendre");
        }
        this.quantiteVenteBillet = quantiteVenteBillet;
    }
    public Date getDateVenteBillet() {
        return dateVenteBillet;
    }
    public void setDateVenteBillet(Date dateVenteBillet)
    throws Exception {
        if(dateVenteBillet==null) {
            throw new Exception("Veuillez entrer un date de vente de billet");
        }
        this.dateVenteBillet = dateVenteBillet;
    }
    public double getCoutRevient() {
        return coutRevient;
    }
    public void setCoutRevient(double coutRevient)
    throws Exception {
        if(coutRevient<=0) {
            throw new Exception("Veuillez entrer un cout de revient plus grand");
        }
        this.coutRevient = coutRevient;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente)
    throws Exception {
        if(prixVente<=0) {
            throw new Exception("Veuillez entrer un prix de vente plus grand");
        }
        this.prixVente = prixVente;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant)
    throws Exception {
        if(etudiant==null) {
            throw new Exception("Veuillez entrer un etudiant");
        }
        this.etudiant = etudiant;
    }
    public int getEstPaye() {
        return estPaye;
    }
    public void setEstPaye(int estPaye)
    throws Exception {
        if(estPaye!=0&&estPaye!=11) {
            throw new Exception("Etat de paiement invalide");
        }
        this.estPaye = estPaye;
    }
}
