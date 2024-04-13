package eval.cinepax.cinepax.service.billet;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.cinepax.cinepax.model.film.Billet;
import eval.cinepax.cinepax.model.film.Film;
import eval.cinepax.cinepax.model.film.Salle;
import eval.cinepax.cinepax.model.film.VChiffreAffaireBFilmJ;
import eval.cinepax.cinepax.model.film.VNbVueBFilmJ;
import eval.cinepax.cinepax.model.place.Place;
import eval.cinepax.cinepax.model.place.VCountPlaceRangee;
import eval.cinepax.cinepax.model.place.VPlaceEtat;
import eval.cinepax.cinepax.model.vente.PlaceVenteBillet;
import eval.cinepax.cinepax.model.vente.VenteBillet;
import eval.cinepax.cinepax.repository.billet.BilletRepository;
import eval.cinepax.cinepax.view.stat.StatVenteBFilm;

@Service
public class BilletService {
    @Autowired
    BilletRepository billetRepository;

    public List<Billet> getAllBillet() {
        return billetRepository.getAllBillet();
    }

    public List<Place> getAllPlace() {
        return billetRepository.getAllPlace();
    }

    public void vendreBillet(VenteBillet venteBillet, PlaceVenteBillet[] listePlaceVenteBillet)
    throws Exception {
        billetRepository.vendreBillet(venteBillet, listePlaceVenteBillet);
    } 

    public Place findPlaceById(int idPlace)
    throws Exception {
        return billetRepository.findPlaceById(idPlace).get();
    }

    public Billet findBilletById(int idBillet)
    throws Exception {
        return billetRepository.findBilletById(idBillet).get();
    }

    public List<StatVenteBFilm> getStatVenteFilm() {
        return billetRepository.getStatVenteFilm();
    }

    public List<VPlaceEtat> getListeEtatPlaceBillet(int idBillet) {
        return billetRepository.getListeEtatPlaceBillet(idBillet);
    }
    
    public List<VCountPlaceRangee> countPlaceByRangee(int idBillet)
    throws Exception {
        return billetRepository.countPlaceByRangee(idBillet);
    }

    public List<VChiffreAffaireBFilmJ> getChiffreAffaireBFilm(Date date) {
        return billetRepository.getChiffreAffaireBFilm(date);
    }

    public double getChiffreAffaireTotalBFilm(Date date) {
        return billetRepository.getChiffreAffaireTotal(date);
    }

    public double getNbVueTotal(Date date) {
        return billetRepository.getNbVueTotal(date);
    }

    public List<VNbVueBFilmJ> getStatNbVueDate(Date date) {
        return billetRepository.getStatNbVueDate(date);
    }

    public List<Film> getAllFilm() {
        return billetRepository.getAllFilm();
    }

    public List<Salle> getAllSalle() {
        return billetRepository.getAllSalle();
    }

    public void insertBillet(Billet billet) {
        billetRepository.insertBillet(billet);
    }

    public List<VenteBillet> getListeVenteBilletOfBillet(int idBillet) {
        return billetRepository.getListeVenteBilletOfBillet(idBillet);
    }

    public List<PlaceVenteBillet> getListePlaceVenteBilletOfVenteBillet(int idVenteBillet) {
        return billetRepository.getListePlaceVenteBilletOfVenteBillet(idVenteBillet);
    }

    public PlaceVenteBillet findPlaceVenteBilletById(int idPlaceVenteBillet) {
        return billetRepository.findPlaceVenteBilletById(idPlaceVenteBillet).get();
    }
}
