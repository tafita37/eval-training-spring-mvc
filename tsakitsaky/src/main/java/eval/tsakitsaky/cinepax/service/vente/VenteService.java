package eval.tsakitsaky.cinepax.service.vente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import eval.tsakitsaky.cinepax.model.produit.Produit;
import eval.tsakitsaky.cinepax.model.produit.pack.Pack;
import eval.tsakitsaky.cinepax.model.produit.pack.PackFormule;
import eval.tsakitsaky.cinepax.model.stat.VStatVenteEtudiant;
import eval.tsakitsaky.cinepax.model.user.Etudiant;
import eval.tsakitsaky.cinepax.model.vente.VenteBillet;
import eval.tsakitsaky.cinepax.repository.produit.ProduitRepository;
import eval.tsakitsaky.cinepax.repository.produit.pack.PackFormuleRepository;
import eval.tsakitsaky.cinepax.repository.produit.pack.PackRepository;
import eval.tsakitsaky.cinepax.repository.stat.VStatVenteEtudiantRepository;
import eval.tsakitsaky.cinepax.repository.vente.VenteRepository;

@Service
public class VenteService {
    @Autowired
    PackRepository packRepository;
    @Autowired
    VenteRepository venteRepository;
    @Autowired
    VStatVenteEtudiantRepository vStatVenteEtudiantRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    PackFormuleRepository packFormuleRepository;

    public List<Pack> getAllPackNS() {
        return packRepository.findByDateSuppressionIsNull();
    }

    public VenteBillet vendreBillet(Etudiant etudiant, int idPack, double quantiteVenteBillet, Date dateVenteBillet)
    throws Exception {
        Pack pack=packRepository.findById(idPack).get();
        VenteBillet venteBillet=new VenteBillet(pack, quantiteVenteBillet, dateVenteBillet, etudiant);
        return venteRepository.save(venteBillet);
    }

    public List<VStatVenteEtudiant> getStatVenteBEtudiant() {
        return vStatVenteEtudiantRepository.findAll();
    }

    public List<Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    public Pack savePack(String nomPack, double prixVente, String photoPack)
    throws Exception {
        Pack pack=new Pack(nomPack, photoPack, prixVente);
        return packRepository.save(pack);
    }

    public Pack findPackById(int idPack) {
        return packRepository.findById(idPack).get();
    }

    public Pack updatePack(int idPack, String nomPack, double prixVente, String photoPack)
    throws Exception {
        Pack pack=this.findPackById(idPack);
        pack.setNomPack(nomPack);
        pack.setPhotoPack(photoPack);
        pack.setPrixVentePack(prixVente);
        return packRepository.save(pack);
    }

    public Pack creerPack(
        String nomPack,  
        String photoPack, 
        int[] idProduit, 
        double[] quantiteProduit, 
        double prixVente
    )
    throws Exception {
        Pack pack=new Pack(nomPack, photoPack, prixVente);
        packRepository.save(pack);
        pack.setListeFormule(new ArrayList<>());
        double coutRevient=0;
        for(int i=0; i<idProduit.length; i++) {
            pack.getListeFormule().add(
                new PackFormule(pack.getIdPack(), produitRepository.findById(idProduit[i]).get(), quantiteProduit[i])
            ); 
            packFormuleRepository.save(pack.getListeFormule().get(i));
            coutRevient+=pack.getListeFormule().get(i).getQuantiteProduit()*
                        pack.getListeFormule().get(i).getProduit().getCoutRevientBUnite();
        }
        pack.setCoutRevientPack(coutRevient);
        return packRepository.save(pack);
    }

    public Pack deltePackById(int idPack) {
        Pack pack=this.findPackById(idPack);
        pack.setDateSuppression(Date.valueOf(LocalDate.now()));
        return packRepository.save(pack);
    }
}
