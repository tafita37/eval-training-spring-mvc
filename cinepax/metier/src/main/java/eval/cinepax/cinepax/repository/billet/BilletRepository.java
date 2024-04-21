package eval.cinepax.cinepax.repository.billet;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import eval.cinepax.cinepax.Constante;
import eval.cinepax.cinepax.model.film.Billet;
import eval.cinepax.cinepax.model.film.Film;
import eval.cinepax.cinepax.model.film.Salle;
import eval.cinepax.cinepax.model.film.Tarif;
import eval.cinepax.cinepax.model.film.VChiffreAffaireBFilmJ;
import eval.cinepax.cinepax.model.film.VNbVueBFilmJ;
import eval.cinepax.cinepax.model.place.Place;
import eval.cinepax.cinepax.model.place.VCountPlaceRangee;
import eval.cinepax.cinepax.model.place.VPlaceEtat;
import eval.cinepax.cinepax.model.vente.PlaceVenteBillet;
import eval.cinepax.cinepax.model.vente.VenteBillet;
import eval.cinepax.cinepax.view.stat.StatVenteBFilm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class BilletRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Billet> getAllBillet() {
        String sql="select*from billet";
        Query query=entityManager.createNativeQuery(sql, Billet.class);
        return query.getResultList();
    }

    @Transactional
    public void insertBilletExcel() {
        String sql="insert into billet(id_billet, id_film, id_salle, date_heure_billet)"+ 
                    "select num_seance, id_film, id_salle, date_heure_seance from v_film_salle_tmp_table";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    public List<Film> getAllFilm() {
        String sql="select*from film";
        Query query=entityManager.createNativeQuery(sql, Film.class);
        return query.getResultList();
    }

    public List<Salle> getAllSalle() {
        String sql="select*from salle";
        Query query=entityManager.createNativeQuery(sql, Salle.class);
        return query.getResultList();
    }

    public List<Place> getAllPlace() {
        String sql="select*from place";
        Query query=entityManager.createNativeQuery(sql, Place.class);
        return query.getResultList();
    }

    public Optional<VenteBillet> findVenteBilletById(int idVenteBillet)
    throws Exception {
        try {
            VenteBillet venteBillet=entityManager.find(VenteBillet.class, idVenteBillet);
            return Optional.ofNullable(venteBillet);
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<PlaceVenteBillet> findPlaceVenteBilletById(int idPlaceVenteBillet) {
        try {
            PlaceVenteBillet placeVenteBillet=entityManager.find(PlaceVenteBillet.class, idPlaceVenteBillet);
            return Optional.ofNullable(placeVenteBillet);
        } catch (Exception e) {
            // e.printStackTrace();
            return Optional.empty();
        }
    }

    public PlaceVenteBillet findPlaceVenteBilletByIdBilletAndIdPlace(int idBillet, int idPlace) {
        String sql="select id_place_vente_billet from v_vente_billet_place where id_billet=:idBillet and id_place=:idPlace";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter("idBillet", idBillet);
        query.setParameter("idPlace", idPlace);
        try {
            int idPlaceVenteBillet= ((Number) query.getSingleResult()).intValue();
            PlaceVenteBillet result= this.findPlaceVenteBilletById(idPlaceVenteBillet).get();
            return result;
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public VenteBillet insertVenteBillet(VenteBillet venteBillet) {
        entityManager.persist(venteBillet);
        return venteBillet;
    }

    @Transactional
    public PlaceVenteBillet insertPlaceVenteBillet(PlaceVenteBillet placeVenteBillet) {
        entityManager.persist(placeVenteBillet);
        return placeVenteBillet;
    }

    public Tarif findTarifByAgeAndHeureBillet(int categorieAge, Time time) {
        String sql="select\n"+
                    "\t *\n"+
                    " from\n"+
                    "\t v_tarif_asc_debut_horaire\n"+
                    " where\n"+
                    "\t categorie_age=:categorieAge\n"+
                    "\t\t and\n"+
                    "\t :time>debut_horaire\n"+
                    " limit 1 offset 0";
        Query query=entityManager.createNativeQuery(sql, Tarif.class);
        query.setParameter("categorieAge", categorieAge);
        query.setParameter("time", time);
        return (Tarif) query.getResultList().get(0);
    }

    @Transactional
    public void vendreBillet(VenteBillet venteBillet, PlaceVenteBillet[] listePlaceVenteBillet)
    throws Exception {
        venteBillet=this.insertVenteBillet(venteBillet);
        PlaceVenteBillet placeVenteBillet=null;
        Tarif tarif=null;
        Time time=null;
        for(int i=0; i<listePlaceVenteBillet.length; i++) {
            placeVenteBillet=this.findPlaceVenteBilletByIdBilletAndIdPlace(
                venteBillet.getBillet().getIdBillet(), 
                listePlaceVenteBillet[i].getPlace().getIdPlace()
            );
            if(placeVenteBillet!=null) {
                throw new Exception("Place "+placeVenteBillet.getPlace().getPlaceName()+" deja reserve");
            }
            listePlaceVenteBillet[i].setVenteBillet(venteBillet);
            time=Constante.toTimeOfTimestamp(venteBillet.getBillet().getDateHeureBillet());
            tarif=this.findTarifByAgeAndHeureBillet(listePlaceVenteBillet[i].getPlaceAdulteEnfant(), time);
            listePlaceVenteBillet[i].setTarif(tarif);
            this.insertPlaceVenteBillet(listePlaceVenteBillet[i]);
        }
    }

    public Optional<Place> findPlaceById(int idPlace)
    throws Exception {
        try {
            Place place=entityManager.find(Place.class, idPlace);
            return Optional.ofNullable(place);
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Billet> findBilletById(int idBillet)
    throws Exception {
        try {
            Billet billet=entityManager.find(Billet.class, idBillet);
            return Optional.ofNullable(billet);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<StatVenteBFilm> getStatVenteFilm() {
        Query query=entityManager.createNativeQuery(
            "select*from v_stat_vente_billet_b_film", 
            StatVenteBFilm.class
        );
        return query.getResultList();
    }

    public Optional<Billet> getFirstBillet() {
        Query query=entityManager.createNativeQuery("select*from billet limit 1 offset 0", Billet.class);
        List listBillet=query.getResultList();
        if(listBillet.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable( (Billet) listBillet.get(0));
    }

    public List<VPlaceEtat> getListeEtatPlaceBillet(int idBillet) {
        if(idBillet==0) {
            idBillet=this.getFirstBillet().get().getIdBillet();
        }
        String sql="select "+
                    "   v_place_rangee.id_place, "+
                    "   v_place_rangee.numero_place, "+
                    "   v_place_rangee.id_rangee, "+
                    "   v_place_rangee.rang_rangee, "+
                    "   coalesce(v_place_etat.occupee, false) as occupee "+
                    "from "+
                    "   ( "+
                    "       select "+ 
                    "           * "+ 
                    "       from "+
                    "           v_place_etat "+ 
                    "       where "+
                    "           id_billet = :idBillet "+
                    "   ) as v_place_etat "+
                    "right join "+
                    "   v_place_rangee "+ 
                    "       on "+
                    "           v_place_rangee.id_place = v_place_etat.id_place";
        Query query=entityManager.createNativeQuery(sql, VPlaceEtat.class);
        query.setParameter("idBillet", idBillet);
        return query.getResultList();
    }

    public List<VPlaceEtat> getListeEtatPlaceRangee(int idBillet, int idRangee) {
        String sql="select\n"+ 
                    "\t v_place_rangee.id_place,\n"+
                    "\t v_place_rangee.numero_place,\n"+ 
                    "\t v_place_rangee.id_rangee,\n"+ 
                    "\t v_place_rangee.rang_rangee,\n"+ 
                    "\t coalesce(v_place_etat.occupee, false) as occupee\n"+ 
                    "\t from\n"+
                    "\t (\n"+
                    "\t\t select\n"+ 
                    "\t\t\t *\n"+ 
                    "\t\t from\n"+ 
                    "\t\t\t v_place_etat\n"+ 
                    "\t\t where\n"+ 
                    "\t\t\t id_billet = :idBillet"+ 
                    "\t ) as v_place_etat\n"+ 
                    " right join\n"+
                    "\t (\n"+
                    "\t\t select\n"+
                    "\t\t\t *\n"+
                    "\t\t from\n"+
                    "\t\t\t v_place_rangee\n"+
                    "\t\t where\n"+ 
                    "\t\t\t id_rangee=:idRangee\n"+
                    "\t) as v_place_rangee\n"+
                    "\t\t on\n"+ 
                    "\t\t\t v_place_rangee.id_place = v_place_etat.id_place\n"+
                    " order by numero_place";
        Query query=entityManager.createNativeQuery(sql, VPlaceEtat.class);
        query.setParameter("idBillet", idBillet);
        query.setParameter("idRangee", idRangee);
        return query.getResultList();
    }

    public List<VCountPlaceRangee> countPlaceByRangee(int idBillet)
    throws Exception {
        Billet billet=null;
        if(idBillet==0) {
            billet=this.getFirstBillet().get();
        } else {
            billet=this.findBilletById(idBillet).get();
        }
        idBillet=billet.getIdBillet();
        Query query=entityManager.createNativeQuery(
            "select id, nb_place, id_rangee from v_count_nb_place_b_rangee where id_salle=:idSalle", 
            VCountPlaceRangee.class
        );
        query.setParameter("idSalle", billet.getSalle().getIdSalle());
        List<Object> listeObject=query.getResultList();
        List<VCountPlaceRangee> result=new ArrayList<>();
        for(int i=0; i<listeObject.size(); i++) {
            VCountPlaceRangee vCountPlaceRangee=(VCountPlaceRangee) listeObject.get(i);
            vCountPlaceRangee.setListeVPlaceEtat(
                this.getListeEtatPlaceRangee(
                    idBillet, 
                    vCountPlaceRangee.getRangee().getIdRangee()
                )
            );
            if(vCountPlaceRangee.getRangee().getIdRangee()==1) {
                System.out.println(vCountPlaceRangee.getListeVPlaceEtat().get(0).isOccupee());
            }
            result.add(vCountPlaceRangee);
        }
        return result;
    }

    public List<VChiffreAffaireBFilmJ> getChiffreAffaireBFilm(Date date) {
        String sql=" select\n"+
                    "\t ROW_NUMBER() OVER() as id,\n"+
                    "\t film.id_film,\n"+
                    "\t coalesce(v_chiffre_affaire_b_film_j.prix_tarif, 0) as prix_tarif,\n"+
                    "\t coalesce((sum(prix_tarif)/(select sum(prix_tarif) from v_vente_billet_place_tarif where date_vente_billet=:date))*100, 0) as pourcentage,\n"+
                    "\t coalesce(v_chiffre_affaire_b_film_j.date_vente_billet, :date) as date_vente_billet\n"+
                    " from\n"+
                    "\t (\n"+
                    "\t\t select"+
                    "\t\t\t *\n"+
                    "\t\t from\n"+ 
                    "\t\t\t v_chiffre_affaire_b_film_j\n"+ 
                    "\t\t where\n"+
                    "\t\t\t date_vente_billet=:date\n"+
                    "\t ) as v_chiffre_affaire_b_film_j\n"+
                    " right join\n"+ 
                    "\t film\n"+ 
                    "\t\t on\n"+ 
                    "\t\t\t v_chiffre_affaire_b_film_j.id_film=film.id_film\n"+
                    "group by\n"+
                    "\t v_chiffre_affaire_b_film_j.prix_tarif,\n"+
                    "\t v_chiffre_affaire_b_film_j.date_vente_billet ,\n"+
                    "\t film.id_film";
                    System.out.println(sql);
        Query query=entityManager.createNativeQuery(
            sql,
            VChiffreAffaireBFilmJ.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<VNbVueBFilmJ> getStatNbVueDate(Date date) {
        String sql=" select\n"+
                    "\t ROW_NUMBER() OVER() as id,\n"+
                    "\t film.id_film,\n"+
                    "\t coalesce(v_nb_vue_b_film_j.nb_vue, 0) as nb_vue,\n"+
                    "\t coalesce((v_nb_vue_b_film_j.nb_vue/(select count(*) from v_vente_billet_place where date_vente_billet=:date))*100, 0) as pourcentage,\n"+
                    "\t coalesce(v_nb_vue_b_film_j.date_vente_billet, :date) as date_vente_billet\n"+
                    " from\n"+
                    "\t (\n"+
                    "\t\t select"+
                    "\t\t\t *\n"+
                    "\t\t from\n"+ 
                    "\t\t\t v_nb_vue_b_film_j\n"+ 
                    "\t\t where\n"+
                    "\t\t\t date_vente_billet=:date\n"+
                    "\t ) as v_nb_vue_b_film_j\n"+
                    " right join\n"+ 
                    "\t film\n"+ 
                    "\t\t on\n"+ 
                    "\t\t\t v_nb_vue_b_film_j.id_film=film.id_film\n"+
                    " group by\n"+
                    "\t film.id_film, \n"+
                    "\t v_nb_vue_b_film_j.date_vente_billet, \n"+
                    "\t v_nb_vue_b_film_j.nb_vue";
        Query query=entityManager.createNativeQuery(
            sql,
            VNbVueBFilmJ.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }

    public double getChiffreAffaireTotal(Date date) {
        Query query=entityManager.createNativeQuery(
            "select prix_total from v_chiffre_affaire_film_total where date_vente_billet=:date"
        );
        query.setParameter("date", date);
        try {
            return ((Number) query.getSingleResult()).doubleValue();
        } catch (NoResultException e) {
            return 0;
        }
    }

    public double getNbVueTotal(Date date) {
        Query query=entityManager.createNativeQuery(
            "select nb_vue from v_nb_vue_total_j where date_vente_billet=:date"
        );
        query.setParameter("date", date);
        try {
            return ((Number) query.getSingleResult()).doubleValue();
        } catch (NoResultException e) {
            return 0;
        }
    }

    @Transactional
    public void insertBillet(Billet billet) {
        entityManager.persist(billet);
    }

    public List<VenteBillet> getListeVenteBilletOfBillet(int idBillet) {
        if(idBillet==0){
            idBillet=this.getFirstBillet().get().getIdBillet();
        }
        Query query=entityManager.createNativeQuery(
            "select*from vente_billet where id_billet=:idBillet", 
            VenteBillet.class
        );
        query.setParameter("idBillet", idBillet);
        return query.getResultList();
    }

    public List<PlaceVenteBillet> getListePlaceVenteBilletOfVenteBillet(int idVenteBillet) {
        Query query=entityManager.createNativeQuery(
            "select*from place_vente_billet where id_vente_billet=:idVenteBillet", 
            PlaceVenteBillet.class
        );
        query.setParameter("idVenteBillet", idVenteBillet);
        return query.getResultList();
    }
}
