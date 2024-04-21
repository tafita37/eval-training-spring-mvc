package eval.tsakitsaky.cinepax.service.vente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eval.tsakitsaky.cinepax.model.axe.Axe;
import eval.tsakitsaky.cinepax.model.axe.Client;
import eval.tsakitsaky.cinepax.model.produit.Produit;
import eval.tsakitsaky.cinepax.model.produit.pack.Pack;
import eval.tsakitsaky.cinepax.model.produit.pack.PackFormule;
import eval.tsakitsaky.cinepax.model.stat.VStatVenteEtudiant;
import eval.tsakitsaky.cinepax.model.user.Etudiant;
import eval.tsakitsaky.cinepax.model.vente.VNombreMontantVentePPack;
import eval.tsakitsaky.cinepax.model.vente.VenteBillet;
import eval.tsakitsaky.cinepax.repository.axe.AxeRepository;
import eval.tsakitsaky.cinepax.repository.axe.ClientRepository;
import eval.tsakitsaky.cinepax.repository.produit.ProduitRepository;
import eval.tsakitsaky.cinepax.repository.produit.pack.PackFormuleRepository;
import eval.tsakitsaky.cinepax.repository.produit.pack.PackRepository;
import eval.tsakitsaky.cinepax.repository.stat.VStatVenteEtudiantRepository;
import eval.tsakitsaky.cinepax.repository.vente.VenteRepository;
import jakarta.transaction.Transactional;
import eval.tsakitsaky.cinepax.repository.vente.*;

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
    @Autowired
    AxeRepository axeRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    VNombreMontantVentePPackRepository VNombreMontantVentePPackRepository;

    public List<Pack> getAllPackNS() {
        return packRepository.findByDateSuppressionIsNull();
    }

    public VenteBillet vendreBillet(Etudiant etudiant, int idPack, int idClient, double quantiteVenteBillet, Date dateVenteBillet)
    throws Exception {
        Pack pack=packRepository.findById(idPack).get();
        VenteBillet venteBillet=new VenteBillet(
            pack, 
            quantiteVenteBillet, 
            dateVenteBillet, 
            etudiant, 
            clientRepository.findById(idClient).get()
        );
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
            coutRevient+=pack.getListeFormule().get(i).getCoutRevientFormule();
        }
        pack.setCoutRevientPack(coutRevient);
        return packRepository.save(pack);
    }

    public Pack deletePackById(int idPack) {
        Pack pack=this.findPackById(idPack);
        pack.setDateSuppression(Date.valueOf(LocalDate.now()));
        return packRepository.save(pack);
    }

    @Transactional(rollbackOn = { Exception.class })
    public void deletePackFormule(PackFormule packFormule)
    throws Exception {
        packFormuleRepository.deleteById(packFormule.getIdPackFormule());
        Pack pack=this.findPackById(packFormule.getIdPack());
        pack.setCoutRevientPack(packFormuleRepository.getCoutRevientPack(pack.getIdPack()));
        packRepository.save(pack);
    }

    public List<PackFormule> findPackFormuleByIdPack(int idPack) {
        return packFormuleRepository.findByIdPack(idPack);
    }

    public PackFormule findPackFormuleById(int idPackFormule) {
        return packFormuleRepository.findById(idPackFormule).get();
    }

    @Transactional(rollbackOn = {Exception.class})
    public PackFormule savePackFormule(PackFormule packFormule)
    throws Exception {
        packFormule=packFormuleRepository.save(packFormule);
        Pack pack=this.findPackById(packFormule.getIdPack());
        pack.setCoutRevientPack(packFormuleRepository.getCoutRevientPack(pack.getIdPack()));
        packRepository.save(pack);
        return packFormule;
    }

    public Produit findProduitById(int idProduit) {
        return produitRepository.findById(idProduit).get();
    }

    public List<VenteBillet> getAllVenteBilletNP() {
        return venteRepository.findByEstPaye(0);
    }

    public List<Axe> getAllAxe() {
        return axeRepository.findAll();
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public List<VNombreMontantVentePPack> getAllNombreMontantAndVente() {
        return VNombreMontantVentePPackRepository.findAll();
    }
}