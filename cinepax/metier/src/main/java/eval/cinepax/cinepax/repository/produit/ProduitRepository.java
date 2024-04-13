package eval.cinepax.cinepax.repository.produit;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import eval.cinepax.cinepax.model.film.produit.Produit;
import eval.cinepax.cinepax.model.film.produit.VChiffreAffaireBProduitJ;
import eval.cinepax.cinepax.model.vente.produit.VenteProduit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ProduitRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Produit> findProduitById(int idProduit) {
        try {
            return Optional.ofNullable(entityManager.find(Produit.class, idProduit));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Produit> getAllProduit() {
        Query query=entityManager.createNativeQuery("select*from produit", Produit.class);
        return query.getResultList();
    }

    @Transactional
    public VenteProduit vendreProduit(VenteProduit venteProduit) {
        if(venteProduit.getIdVenteProduit()==0) {
            entityManager.persist(venteProduit);
        } else {
            entityManager.merge(venteProduit);
        }
        return venteProduit;
    }

    public double getChiffreAffaireProduitTotal(Date date) {
        Query query=entityManager.createNativeQuery(
            "select ca_produit from v_chiffre_affaire_produit_total where date_vente_produit=:date"
        );
        query.setParameter("date", date);
        try {
            return ((Number) query.getSingleResult()).doubleValue();
        } catch (NoResultException e) {
            return 0;
        }
    }

    public List<VChiffreAffaireBProduitJ> getChiffreAffaireBProduit(Date date) {
        String sql=" select\n"+
                    "\t ROW_NUMBER() OVER() as id,\n"+
                    "\t produit.id_produit,\n"+
                    "\t coalesce(v_chiffre_affaire_b_produit_j.ca_produit, 0) as ca_produit,\n"+
                    "\t coalesce(coalesce(v_chiffre_affaire_b_produit_j.ca_produit, 0)/(select sum(quantite_vendu*prix_vente) from vente_produit where date_vente_produit=:date), 0)*100 as pourcentage,\n"+
                    "\t coalesce(v_chiffre_affaire_b_produit_j.date_vente_produit, :date) as date_vente_produit\n"+
                    " from\n"+
                    "\t (\n"+
                    "\t\t select\n"+
                    "\t\t\t *\n"+
                    "\t\t from\n"+ 
                    "\t\t\t v_chiffre_affaire_b_produit_j\n"+ 
                    "\t\t where\n"+
                    "\t\t\t date_vente_produit=:date\n"+
                    "\t ) as v_chiffre_affaire_b_produit_j\n"+
                    " right join\n"+ 
                    "\t produit\n"+ 
                    "\t\t on\n"+ 
                    "\t\t\t v_chiffre_affaire_b_produit_j.id_produit=produit.id_produit";
        Query query=entityManager.createNativeQuery(
            sql,
            VChiffreAffaireBProduitJ.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }
}
