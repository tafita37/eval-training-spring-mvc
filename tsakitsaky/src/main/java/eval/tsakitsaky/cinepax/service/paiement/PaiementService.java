package eval.tsakitsaky.cinepax.service.paiement;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eval.tsakitsaky.cinepax.model.paiement.Paiement;
import eval.tsakitsaky.cinepax.model.paiement.VEtatPaiement;
import eval.tsakitsaky.cinepax.model.paiement.VEtatPaiementBEtudiant;
import eval.tsakitsaky.cinepax.model.vente.VenteBillet;
import eval.tsakitsaky.cinepax.repository.paiement.PaiementRepository;
import eval.tsakitsaky.cinepax.repository.paiement.VEtatPaiementBEtudiantRepository;
import eval.tsakitsaky.cinepax.repository.paiement.VEtatPaiementRepository;
import eval.tsakitsaky.cinepax.repository.vente.VenteRepository;

@Service
public class PaiementService {
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    VenteRepository venteRepository;
    @Autowired
    VEtatPaiementRepository vEtatPaiementRepository;
    @Autowired
    VEtatPaiementBEtudiantRepository vEtatPaiementBEtudiantRepository;

    public Paiement savePaiement(int idVenteBillet, double montantPayer, Date datePaiement)
    throws Exception {
        VenteBillet venteBillet=venteRepository.findById(idVenteBillet).get();
        if(venteBillet.getEstPaye()!=0) {
            throw new Exception("Le paiement de la vente numero "+idVenteBillet+" est deja termine");
        } 
        Paiement paiement=new Paiement(venteBillet, montantPayer, datePaiement);
        paiement=paiementRepository.save(paiement);
        VEtatPaiement vEtatPaiement=vEtatPaiementRepository.findByVenteBillet(venteBillet).get();
        if(vEtatPaiement.getResteAPayer()==0) {
            venteBillet.setEstPaye(11);
            venteRepository.save(venteBillet);
        }
        return paiement;
    }

    public List<VEtatPaiementBEtudiant> getAllEtatDePaiementBEtudiant() {
        return vEtatPaiementBEtudiantRepository.findAll();
    }
}
