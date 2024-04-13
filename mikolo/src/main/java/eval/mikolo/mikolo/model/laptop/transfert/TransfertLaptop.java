package eval.mikolo.mikolo.model.laptop.transfert;

import java.sql.Date;
import eval.mikolo.mikolo.model.laptop.Laptop;
import eval.mikolo.mikolo.model.pdv.PointVente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transfert_laptop")
public class TransfertLaptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfert_laptop", nullable = false)
    int idTransfertLaptop;
    @ManyToOne
    @JoinColumn(name = "id_point_vente", nullable = false)
    PointVente pointVente;
    @ManyToOne
    @JoinColumn(name = "id_laptop", nullable = false)
    Laptop laptop;
    @Column(name = "quantite_laptop", nullable = false)
    int quantiteLaptop;
    @Column(name = "date_transfert", nullable = false)
    Date dateTransfertLaptop;
    @Column(name = "etat_transfert", nullable = false)
    int etatTransfertLaptop;
    @Column(name = "prix_achat", nullable = false)
    double prixAchat;
    @Column(name = "prix_vente", nullable = false)
    double prixVente;
    public TransfertLaptop(
        int idTransfertLaptop, 
        PointVente pointVente, 
        Laptop laptop, 
        int quantiteLaptop, 
        Date dateTransfertLaptop, 
        int etatTransfertLaptop
    )
    throws Exception {
        this.setIdTransfertLaptop(idTransfertLaptop);
        this.setPointVente(pointVente);
        this.setLaptop(laptop);
        this.setQuantiteLaptop(quantiteLaptop);
        this.setDateTransfertLaptop(dateTransfertLaptop);
        this.setEtatTransfertLaptop(etatTransfertLaptop);
    }
    public TransfertLaptop() {
    }
    public int getIdTransfertLaptop() {
        return idTransfertLaptop;
    }
    public void setIdTransfertLaptop(int idTransfertLaptop)
    throws Exception {
        if(idTransfertLaptop<=0) {
            throw new Exception("Veuillez entrer un id de transfert de laptop valide");
        }
        this.idTransfertLaptop = idTransfertLaptop;
    }
    public Laptop getLaptop() {
        return laptop;
    }
    public void setLaptop(Laptop laptop)
    throws Exception {
        if(laptop==null) {
            throw new Exception("Veuillez entrer un laptop");
        }
        this.laptop = laptop;
    }
    public int getQuantiteLaptop() {
        return quantiteLaptop;
    }
    public void setQuantiteLaptop(int quantiteLaptop)
    throws Exception {
        if(quantiteLaptop<=0) {
            throw new Exception("Veuillez entrer une quantite a transferer plus grande");
        }
        this.quantiteLaptop = quantiteLaptop;
    }
    public Date getDateTransfertLaptop() {
        return dateTransfertLaptop;
    }
    public void setDateTransfertLaptop(Date dateTransfertLaptop)
    throws Exception {
        if(dateTransfertLaptop==null) {
            throw new Exception("Veuillez entrer une date de transfert de laptop");
        }
        this.dateTransfertLaptop = dateTransfertLaptop;
    }
    public int getEtatTransfertLaptop() {
        return etatTransfertLaptop;
    }
    public void setEtatTransfertLaptop(int etatTransfertLaptop)
    throws Exception {
        if(etatTransfertLaptop!=0&&etatTransfertLaptop!=11) {
            throw new Exception("Etat de transfert invalide");
        }
        this.etatTransfertLaptop = etatTransfertLaptop;
    }
    public PointVente getPointVente() {
        return pointVente;
    }
    public void setPointVente(PointVente pointVente)
    throws Exception {
        if(pointVente==null) {
            throw new Exception("Veuillez entrer un point de vente");
        }
        this.pointVente = pointVente;
    }
    public double getPrixAchat() {
        return prixAchat;
    }
    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
}
