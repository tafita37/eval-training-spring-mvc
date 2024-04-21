package eval.tsakitsaky.cinepax.controller.paiement;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import eval.tsakitsaky.cinepax.config.RequireRoleMapping;
import eval.tsakitsaky.cinepax.service.paiement.PaiementService;
import eval.tsakitsaky.cinepax.service.vente.VenteService;

@RestController
@RequestMapping("/paiement")
@RequireRoleMapping("etudiant")
public class PaiementController {
    @Autowired
    VenteService venteService;
    @Autowired
    PaiementService paiementService;

    @GetMapping("/formPaiementVente")
    public ModelAndView getFormPaiement() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formPaiement");
        modelAndView.addObject("listeVenteBillet", venteService.getAllVenteBilletNP());
        return modelAndView;
    }

    @PostMapping("/payerVente")
    public ModelAndView payerVente( 
        @RequestParam("idVenteBillet") int idVenteBillet, 
        @RequestParam("montantPayer") double montantPayer, 
        @RequestParam("datePaiement") Date datePaiement
    )
    throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/paiement/formPaiementVente");
        paiementService.savePaiement(idVenteBillet, montantPayer, datePaiement);
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/etatPaiementBEtudiant")
    public ModelAndView getEtatDePaiementBEtudiant() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listeEtatPaiementBEtudiant");
        modelAndView.addObject("listeEtatPaiement", paiementService.getAllEtatDePaiementBEtudiant());
        return modelAndView;
    }
}
