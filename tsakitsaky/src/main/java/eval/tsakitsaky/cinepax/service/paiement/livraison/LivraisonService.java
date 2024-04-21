package eval.tsakitsaky.cinepax.service.paiement.livraison;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eval.tsakitsaky.cinepax.model.paiement.livraison.VEtatLivraison;
import eval.tsakitsaky.cinepax.repository.paiement.livraison.VEtatLivraisonRepository;

@Service
public class LivraisonService {
    @Autowired
    VEtatLivraisonRepository vEtatLivraisonRepository;

    public List<VEtatLivraison> getAllEtatLivraison() {
        return vEtatLivraisonRepository.findAll();
    }

    public VEtatLivraison getEtatLivraisonById(int idEtatLivraison) {
        return vEtatLivraisonRepository.findById(idEtatLivraison).get();
    }
}
