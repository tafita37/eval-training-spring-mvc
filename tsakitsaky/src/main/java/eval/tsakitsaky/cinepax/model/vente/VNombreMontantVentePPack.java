package eval.tsakitsaky.cinepax.model.vente;

import java.util.ArrayList;
import java.util.List;

import eval.tsakitsaky.cinepax.model.produit.pack.Pack;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_nombre_montant_vente_p_pack")
public class VNombreMontantVentePPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int idVNombreMontantVentePPack;
    @ManyToOne
    @JoinColumn(name = "id_pack")
    Pack pack;
    @Column(name = "quantite_vente_billet")
    double quantiteVenteBillet;
    @Column(name = "prix_vente")
    double prixVente;
    public VNombreMontantVentePPack() {
    }
    public int getIdVNombreMontantVentePPack() {
        return idVNombreMontantVentePPack;
    }
    public void setIdVNombreMontantVentePPack(int idVNombreMontantVentePPack) {
        this.idVNombreMontantVentePPack = idVNombreMontantVentePPack;
    }
    public Pack getPack() {
        return pack;
    }
    public void setPack(Pack pack) {
        this.pack = pack;
    }
    public double getQuantiteVenteBillet() {
        return quantiteVenteBillet;
    }
    public void setQuantiteVenteBillet(double quantiteVenteBillet) {
        this.quantiteVenteBillet = quantiteVenteBillet;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public ArrayList<Object> getListeAffichageExcel() {
        ArrayList<Object> result=new ArrayList<>();
        result.add(this.getPack().getNomPack());
        result.add(this.getQuantiteVenteBillet());
        result.add(this.getPrixVente());
        return result;
    }
}
