package eval.cinepax.cinepax.model.vente;

import eval.cinepax.cinepax.model.film.Tarif;
import eval.cinepax.cinepax.model.place.Place;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "place_vente_billet")
public class PlaceVenteBillet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place_vente_billet")
    int idPlaceVenteBillet;
    @ManyToOne
    @JoinColumn(name = "id_place")
    Place place;
    @ManyToOne
    @JoinColumn(name = "id_vente_billet")
    VenteBillet venteBillet;
    @Transient
    int placeAdulteEnfant;
    @ManyToOne
    @JoinColumn(name = "id_tarif")
    Tarif tarif;
    public PlaceVenteBillet() {
    }
    public PlaceVenteBillet(VenteBillet venteBillet, Place place)
    throws Exception {
        this.setVenteBillet(venteBillet);
        this.setPlace(place);
    }
    public PlaceVenteBillet(Place place)
    throws Exception {
        this.setPlace(place);
    }
    public int getIdPlaceVenteBillet() {
        return idPlaceVenteBillet;
    }
    public void setIdPlaceVenteBillet(int idPlaceVenteBillet)
    throws Exception {
        if(idPlaceVenteBillet<=0) {
            throw new Exception("Veuillez entrer un id de vente de place de billet valide");
        }
        this.idPlaceVenteBillet = idPlaceVenteBillet;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place)
    throws Exception {
        if(place==null) {
            throw new Exception("Veuillez entrer une place");
        }
        if(
            this.getVenteBillet()!=null
                &&
            place.getRangee().getSalle().getIdSalle()!=this.getVenteBillet().getBillet().getSalle().getIdSalle()
        ) {
            throw new Exception(
                "La place "+
                this.getPlace().getPlaceName()+
                " n'appartient pas a la salle "+
                this.getVenteBillet().getBillet().getSalle().getNomSalle()
            );
        }
        this.place = place;
    }
    public VenteBillet getVenteBillet() {
        return venteBillet;
    }
    public void setVenteBillet(VenteBillet venteBillet)
    throws Exception {
        if(venteBillet==null) {
            throw new Exception("Veuillez entrer une vente de billet");
        }
        if(
            this.getPlace()!=null
                &&
            this.getPlace().getRangee().getSalle().getIdSalle()!=venteBillet.getBillet().getSalle().getIdSalle()
        ) {
            throw new Exception(
                "La place "+
                this.getPlace().getPlaceName()+
                " n'appartient pas a la salle "+
                venteBillet.getBillet().getSalle().getNomSalle()
            );
        }
        this.venteBillet = venteBillet;
    }
    public int getPlaceAdulteEnfant() {
        return placeAdulteEnfant;
    }
    public void setPlaceAdulteEnfant(int placeAdulteEnfant)
    throws Exception {
        if(placeAdulteEnfant!=0&&placeAdulteEnfant!=11) {
            throw new Exception("Il doit s'agir soit d'une place adulte soit d'une place enfant");
        }
        this.placeAdulteEnfant = placeAdulteEnfant;
    }
    public Tarif getTarif() {
        return tarif;
    }
    public void setTarif(Tarif tarif)
    throws Exception {
        if(tarif==null) {
            throw new Exception("Veuillez entrer un tarif");
        }
        this.tarif = tarif;
    }
}