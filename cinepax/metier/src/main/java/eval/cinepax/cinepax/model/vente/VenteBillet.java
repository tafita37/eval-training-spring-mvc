package eval.cinepax.cinepax.model.vente;

import java.sql.Date;

import eval.cinepax.cinepax.model.film.Billet;
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
    @Column(name = "id_vente_billet", nullable = false, unique = true)
    int idVenteBillet;
    @JoinColumn(name = "id_billet", nullable = false)
    @ManyToOne
    Billet billet;
    @Column(name = "date_vente_billet", nullable = false)
    Date dateVenteBillet;
    public VenteBillet() {
    }
    public VenteBillet(Billet billet, Date dateVenteBillet)
    throws Exception {
        this.setBillet(billet);
        this.setDateVenteBillet(dateVenteBillet);
    }
    public int getIdVenteBillet() {
        return idVenteBillet;
    }
    public void setIdVenteBillet(int idVenteBillet)
    throws Exception {
        if(idVenteBillet<=0) {
            throw new Exception("Veuillez entrer un id de billet valide");
        }
        this.idVenteBillet = idVenteBillet;
    }
    public Billet getBillet() {
        return billet;
    }
    public void setBillet(Billet billet)
    throws Exception {
        if(billet==null) {
            throw new Exception("Veuillez entrer un billet");
        }
        this.billet = billet;
    }
    public Date getDateVenteBillet() {
        return dateVenteBillet;
    }
    public void setDateVenteBillet(Date dateVenteBillet)
    throws Exception {
        if(dateVenteBillet==null) {
            throw new Exception("Veuillez entrer une date de vente de billet");
        }
        this.dateVenteBillet = dateVenteBillet;
    }
}
