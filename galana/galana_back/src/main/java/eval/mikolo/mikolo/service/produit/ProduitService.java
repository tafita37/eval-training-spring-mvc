package eval.mikolo.mikolo.service.produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.mikolo.mikolo.model.autre.ChoixVente;
import eval.mikolo.mikolo.model.produit.MouvementProduit;
import eval.mikolo.mikolo.model.produit.Produit;
import eval.mikolo.mikolo.model.produit.ResteStockProduit;
import eval.mikolo.mikolo.model.profile.Station;
import eval.mikolo.mikolo.repository.produit.ProduitRepository;
import eval.mikolo.mikolo.service.profile.LoginService;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    LoginService loginService;

    public void newMouvement(MouvementProduit mouvementProduit, int entreeSortie, String token)
    throws Exception {
        Station station=loginService.findStationConnectedByToken(token);
        mouvementProduit.setStation(station);
        Produit produit=produitRepository.findProduitByIdProduit(mouvementProduit.getProduit().getIdProduit()).get();
        mouvementProduit.setProduit(produit);
        mouvementProduit.setPrixRevient(produit.getPrixRevient());
        mouvementProduit.setPrixVente(produit.getPrixVente());
        if(entreeSortie==0) {
            if(mouvementProduit.getEntreeProduit()<=0) {
                throw new Exception(
                    "Veuillez ajouter une plus grande quantite de "+mouvementProduit.getProduit().getNomProduit()
                );
            }
            if(mouvementProduit.getSortieProduit()>0) {
                throw new Exception("Vous ne pouvez faire qu'une entree de stock");
            }
        } else {
            ResteStockProduit resteStockProduit=produitRepository.getResteStockProduit(
                produit.getIdProduit(), 
                station.getIdStation(), 
                mouvementProduit.getDateMouvementProduit()
            );
            if(mouvementProduit.getSortieProduit()<=0) {
                throw new Exception(
                    "Veuillez faire sortir une plus grande quantite de "+mouvementProduit.getProduit().getNomProduit()
                );
            }
            if(mouvementProduit.getEntreeProduit()>0) {
                throw new Exception("Vous ne pouvez faire qu'une sortie de stock");
            }
            if(resteStockProduit.getResteStock()<mouvementProduit.getSortieProduit()) {
                throw new Exception("Vous n'avez pas assez de "+produit.getNomProduit()+" en stock");
            }
        }
        produitRepository.newMouvementProduit(mouvementProduit);
    }

    public void saveProduit(Produit produit) {
        produitRepository.saveProduit(produit);
    }

    public void vendreProduit(ChoixVente choixVente, String token)
    throws Exception {
        Station station=loginService.findStationConnectedByToken(token);
        choixVente.setProduit(produitRepository.findProduitByIdProduit(choixVente.getProduit().getIdProduit()).get());
        MouvementProduit mouvementProduit=new MouvementProduit(
            choixVente.getProduit(), 
            station, 
            0, 
            choixVente.getRealQuantite(), 
            choixVente.getDate(), 
            choixVente.getProduit().getPrixRevient(), 
            choixVente.getProduit().getPrixVente() 
        );
        this.newMouvement(mouvementProduit, 1, token);
    }

    public List<Produit> findAllProduit() {
        return produitRepository.findAllProduit();
    }

    public List<Produit> findAllProduit(int numPage) {
        return produitRepository.findAllProduit(numPage);
    }

    public int countNbPageProduit() {
        return produitRepository.countNbPageProduit();
    }

    public List<MouvementProduit> getListeEntree(String token, int numPage) {
        Station station=loginService.findStationConnectedByToken(token);
        return produitRepository.getListeEntreeStock(numPage, station.getIdStation());
    }

    public int countNbPageEntreeStock(String token) {
        Station station=loginService.findStationConnectedByToken(token);
        return produitRepository.countNbPageEntreeStock(station.getIdStation());
    }
}
