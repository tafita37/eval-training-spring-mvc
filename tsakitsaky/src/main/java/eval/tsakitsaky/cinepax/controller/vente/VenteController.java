package eval.tsakitsaky.cinepax.controller.vente;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import eval.tsakitsaky.cinepax.config.RequireRole;
import eval.tsakitsaky.cinepax.config.UploadFileService;
import eval.tsakitsaky.cinepax.model.produit.Produit;
import eval.tsakitsaky.cinepax.model.produit.pack.Pack;
import eval.tsakitsaky.cinepax.model.user.Etudiant;
import eval.tsakitsaky.cinepax.model.vente.VenteBillet;
import eval.tsakitsaky.cinepax.service.vente.VenteService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ventePack")
public class VenteController {
    @Autowired
    VenteService venteService;
    @Value("${app.baseUrl}")
    private String baseUrl;
    @Autowired
    UploadFileService uploadFileService;

    @GetMapping("/formVentePack")
    @RequireRole("etudiant")
    public ModelAndView loadFormVentePack() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formVentePack");
        modelAndView.addObject("listePack", venteService.getAllPackNS());
        return modelAndView;
    }

    @PostMapping("/vendrePack")
    @RequireRole("etudiant")
    public ModelAndView vendrePack(
        @RequestParam(name = "idPack") int idPack, 
        @RequestParam(name = "quantiteVenteBillet") double quantiteVenteBillet, 
        @RequestParam(name = "dateVenteBillet") Date dateVenteBillet,
        HttpSession httpSession
    )
    throws Exception {
        Etudiant etudiant = (Etudiant) httpSession.getAttribute("etudiant");
        VenteBillet billetVendu=venteService.vendreBillet(etudiant, idPack, quantiteVenteBillet, dateVenteBillet);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView();
        redirectView.setUrl("/ventePack/formVentePack");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/statVenteByEtudiant")
    @RequireRole("etudiant")
    public ModelAndView getStatVenteByEtudiant() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("listeStatVente", venteService.getStatVenteBEtudiant());
        modelAndView.setViewName("dynamique/listeStatVenteBEtudiant");
        return modelAndView;
    }

    @GetMapping("/listePack")
    @RequireRole("etudiant")
    public ModelAndView getListePack() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listePack");
        String[] cssListe={"liste.css"};
        modelAndView.addObject("cssList", cssListe);
        String[] jsListe={"formModifPack.js", "file-upload.js"};
        modelAndView.addObject("jssFiles", jsListe);
        modelAndView.addObject("listePack", venteService.getAllPackNS());
        return modelAndView;
    }

    @GetMapping("/formInsertPack")
    public ModelAndView loadFormInsertPack() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formNewPack");
        modelAndView.addObject("listeProduit", venteService.getAllProduit());
        String[] jsListe={"formNewPack.js", "file-upload.js"};
        modelAndView.addObject("jssFiles", jsListe);
        return modelAndView;
    }

    @PostMapping("/pack/creerPack")
    public ModelAndView creerPack(
        @RequestParam("nomPack") String nomPack, 
        @RequestParam("photoPack") MultipartFile photoPack, 
        @RequestParam("idProduit[]") int[] idProduit,
        @RequestParam("quantiteProduit[]") double[] quantiteProduit,
        @RequestParam("prixVente") double prixVente
    )
    throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/ventePack/formInsertPack");
        String pictureName = uploadFileService.uploadFile(photoPack, "/assets/images/pack/");
        venteService.creerPack(nomPack, pictureName, idProduit, quantiteProduit, prixVente);
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/produit/allProduit")
    public List<Produit> getAllProduit() {
        return venteService.getAllProduit();
    }

    @PostMapping("/pack/updatePack")
    public ModelAndView updatePack(
        @RequestParam("idPack") int idPack, 
        @RequestParam("nomPack") String nomPack, 
        @RequestParam("prixVente") double prixVente, 
        @RequestParam(name = "photoPack", required = false) MultipartFile photoPack
    )
    throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/ventePack/listePack");
        Pack pack=venteService.findPackById(idPack);
        String pictureName=null;
        if(
            photoPack.getOriginalFilename()!=null
                &&
            photoPack.getOriginalFilename().length()!=0
                &&
            pack.getPhotoPack().compareTo(photoPack.getOriginalFilename())!=0
        ) {
            pictureName = uploadFileService.uploadFile(photoPack, "/assets/images/pack/");
        } else {
            pictureName=pack.getPhotoPack();
        }
        venteService.updatePack(idPack, nomPack, prixVente, pictureName);
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/pack/deletePackById/{idPack}")
    public ModelAndView deletePackById(@PathVariable int idPack) {
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/ventePack/listePack");
        venteService.deltePackById(idPack);
        modelAndView.setView(redirectView);
        return modelAndView;
    }
}