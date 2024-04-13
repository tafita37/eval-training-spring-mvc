package eval.cinepax.cinepax.service.produit;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.cinepax.cinepax.model.film.produit.Produit;
import eval.cinepax.cinepax.model.film.produit.VChiffreAffaireBProduitJ;
import eval.cinepax.cinepax.model.vente.produit.VenteProduit;
import eval.cinepax.cinepax.repository.produit.ProduitRepository;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;

    public List<Produit> getAllProduit() {
        return produitRepository.getAllProduit();
    }

    public Produit findProduitById(int idProduit) {
        return produitRepository.findProduitById(idProduit).get();
    }

    public VenteProduit vendreProduit(int idProduit, Date dateVenteProduit, int quantiteVenteProduit)
    throws Exception {
        Produit produit=this.findProduitById(idProduit);
        VenteProduit venteProduit=new VenteProduit(produit, dateVenteProduit, quantiteVenteProduit);
        return produitRepository.vendreProduit(venteProduit);
    }

    public double getChiffreAffaireProduitTotal(Date date) {
        return produitRepository.getChiffreAffaireProduitTotal(date);
    }

    public List<VChiffreAffaireBProduitJ> getChiffreAffaireBProduit(Date date) {
        return produitRepository.getChiffreAffaireBProduit(date);
    }
}
