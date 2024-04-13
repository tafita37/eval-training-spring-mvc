package eval.mikolo.mikolo.repository.produit;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import eval.mikolo.mikolo.model.produit.MouvementProduit;
import eval.mikolo.mikolo.model.produit.Produit;
import eval.mikolo.mikolo.model.produit.ResteStockProduit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ProduitRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void newMouvementProduit(MouvementProduit mouvementProduit) {
        entityManager.persist(mouvementProduit);
    }

    @Transactional
    public void saveProduit(Produit produit) {
        if(produit.getIdProduit()==0) {
            entityManager.persist(produit);
        } else {
            entityManager.merge(produit);
        }
    }

    public Optional<Produit> findProduitByIdProduit(int idProduit) {
        try {
            Produit produit =  entityManager.find(Produit.class, idProduit);
            return Optional.ofNullable(produit);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Produit> findAllProduit() {
        Query query=entityManager.createNativeQuery("select*from produit", Produit.class);
        return query.getResultList();
    }

    public List<Produit> findAllProduit(int numPage) {
        Query query=entityManager.createNativeQuery(
            "select*from produit limit 5 offset :debut", 
            Produit.class
        );
        query.setParameter("debut", (numPage-1)*5);
        return query.getResultList();
    }

    public int countNbPageProduit() {
        Query query=entityManager.createNativeQuery("select*from v_nb_pages_produit");
        int result=((Number) query.getSingleResult()).intValue();
        return result;
    }
    
    public ResteStockProduit getResteStockProduit(int idProduit, int idStation, Date dateMouvementProduit) {
        String sql="select "+
                    "id, "+
                    "id_produit, "+
                    "id_station, "+
                    "sum(reste_stock) as reste_stock "+
                    "from "+
                    "v_reste_produit_stock_j "+
                    "where "+
                    "date_mouvement_produit<=:dateMouvementProduit "+
                    "and "+
                    "id_produit=:idProduit "+
                    "and "+
                    "id_station=:idStation "+
                    "group by "+
                    "id, "+
                    "id_produit, "+
                    "id_station";
                    System.out.println(sql);
        Query query=entityManager.createNativeQuery(sql, ResteStockProduit.class);
        query.setParameter("dateMouvementProduit", dateMouvementProduit);
        query.setParameter("idProduit", idProduit);
        query.setParameter("idStation", idStation);
        List<ResteStockProduit> liste=query.getResultList();
        return liste.get(0);
    }

    public List<MouvementProduit> getListeEntreeStock(int numPage, int idStation) {
        Query query=entityManager.createNativeQuery(
            "select*from v_entree_stock_produit where id_station=:idStation limit 5 offset :debut", 
            MouvementProduit.class
        );
        query.setParameter("debut", (numPage-1)*5);
        query.setParameter("idStation", idStation);
        return query.getResultList();
    }

    public int countNbPageEntreeStock(int idStation) {
        String sql="select nb_page_entree_stock_produit from v_nb_pages_entree_stock_produit where id_station=:idStation";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter("idStation", idStation);
        int result=((Number) query.getSingleResult()).intValue();
        return result;
    }
}
