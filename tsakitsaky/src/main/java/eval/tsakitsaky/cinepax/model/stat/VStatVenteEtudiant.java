package eval.tsakitsaky.cinepax.model.stat;

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
@Table(name = "v_stat_vente_etudiant")
public class VStatVenteEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "quantite_vendu")
    double quantiteVendu;
    @ManyToOne
    @JoinColumn(name = "id_pack")
    Pack pack;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;
    public VStatVenteEtudiant(int id, double quantiteVendu, Pack pack, Etudiant etudiant) {
        this.id = id;
        this.quantiteVendu = quantiteVendu;
        this.pack = pack;
        this.etudiant = etudiant;
    }
    public VStatVenteEtudiant() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getQuantiteVendu() {
        return quantiteVendu;
    }
    public void setQuantiteVendu(double quantiteVendu)
    throws Exception {
        this.quantiteVendu = quantiteVendu;
    }
    public Pack getPack() {
        return pack;
    }
    public void setPack(Pack pack) {
        this.pack = pack;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
