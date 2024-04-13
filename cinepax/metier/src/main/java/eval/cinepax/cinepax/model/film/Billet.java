package eval.cinepax.cinepax.model.film;

import java.sql.Timestamp;

import eval.cinepax.cinepax.Constante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "billet")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_billet", unique = true, nullable = false)
    int idBillet;
    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    Film film;
    @ManyToOne
    @JoinColumn(name = "id_salle", nullable = false)
    Salle salle;
    @Column(name = "date_heure_billet", nullable = false)
    Timestamp dateHeureBillet;
    public Billet(Film film, Salle salle, Timestamp dateHeureBillet)
    throws Exception {
        this.setFilm(film);
        this.setSalle(salle);
        this.setDateHeureBillet(dateHeureBillet);
    }
    public Billet(int idBillet)
    throws Exception {
        this.setIdBillet(idBillet);
    }
    public Billet() {
    }
    public int getIdBillet() {
        return idBillet;
    }
    public void setIdBillet(int idBillet)
    throws Exception {
        if(idBillet<=0) {
            throw new Exception("Veuillez entrer un id de billet valide");
        }
        this.idBillet = idBillet;
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film)
    throws Exception {
        if(film==null) {
            throw new Exception("Veuillez entrer un film");
        }
        this.film = film;
    }
    public Salle getSalle() {
        return salle;
    }
    public void setSalle(Salle salle)
    throws Exception {
        if(salle==null) {
            throw new Exception("Veuillez entrer une salle");
        }
        this.salle = salle;
    }
    public Timestamp getDateHeureBillet() {
        return dateHeureBillet;
    }
    public void setDateHeureBillet(Timestamp dateHeureBillet)
    throws Exception {
        if(dateHeureBillet==null) {
            throw new Exception("Veuillez entrer une date et heure de projection");
        }
        this.dateHeureBillet = dateHeureBillet;
    }

    public String getNameBillet() {
        return this.getFilm().getNomFilm()+" "+this.getSalle().getNomSalle()+" "+this.getDateHeureBillet();
    }

    public String getHoursBillet() {
        return Constante.getHeureMinuteOfTimestamp(dateHeureBillet);
    }

    public String getDateBillet() {
        return Constante.getDateOfTimestamp(dateHeureBillet);
    }
}
