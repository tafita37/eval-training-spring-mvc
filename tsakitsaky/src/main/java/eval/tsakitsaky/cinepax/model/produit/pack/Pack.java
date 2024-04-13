package eval.tsakitsaky.cinepax.model.produit.pack;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pack")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack")
    int idPack;
    @Column(name = "nom_pack")
    String nomPack;
    @Column(name = "photo_pack")
    String photoPack;
    @Column(name = "cout_revient_pack")
    double coutRevientPack;
    @Column(name = "prix_vente_pack")
    double prixVentePack;
    @OneToMany(mappedBy = "idPack")
    List<PackFormule> listeFormule;
    @Column(name = "date_suppression")
    Date dateSuppression;
    public Pack(int idPack, String nomPack, String photoPack, double prixVentePack)
    throws Exception {
        this.setIdPack(idPack);
        this.setNomPack(nomPack);
        this.setPhotoPack(photoPack);
        this.setPrixVentePack(prixVentePack);
    }
    public Pack(String nomPack, String photoPack, double prixVentePack)
    throws Exception {
        this.setNomPack(nomPack);
        this.setPhotoPack(photoPack);
        this.setPrixVentePack(prixVentePack);
    }
    public Pack() {
    }
    public int getIdPack() {
        return idPack;
    }
    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }
    public String getNomPack() {
        return nomPack;
    }
    public void setNomPack(String nomPack)
    throws Exception {
        if(nomPack==null||nomPack.length()==0) {
            throw new Exception("Veuillez entrer un nom de pack");
        }
        this.nomPack = nomPack;
    }
    public String getPhotoPack() {
        return photoPack;
    }
    public void setPhotoPack(String photoPack)
    throws Exception {
        if(photoPack==null||photoPack.length()==0) {
            throw new Exception("Veuillez entrer un nom pour le fichier de la photo");
        }
        this.photoPack = photoPack;
    }
    public double getCoutRevientPack() {
        return coutRevientPack;
    }
    public void setCoutRevientPack(double coutRevientPack)
    throws Exception {
        if(coutRevientPack<=0) {
            throw new Exception("Veuillez entrer un cout de revient de pack plus grand");
        }
        this.coutRevientPack = coutRevientPack;
    }
    public double getPrixVentePack() {
        return prixVentePack;
    }
    public void setPrixVentePack(double prixVentePack)
    throws Exception {
        if(prixVentePack<=0) {
            throw new Exception("Veuillez entrer un prix de vente de pack plus grand");
        }
        this.prixVentePack = prixVentePack;
    }
    public List<PackFormule> getListeFormule() {
        return listeFormule;
    }
    public void setListeFormule(List<PackFormule> listeFormule) {
        this.listeFormule = listeFormule;
    }
    public Date getDateSuppression() {
        return dateSuppression;
    }
    public void setDateSuppression(Date dateSuppression) {
        this.dateSuppression = dateSuppression;
    }
}
