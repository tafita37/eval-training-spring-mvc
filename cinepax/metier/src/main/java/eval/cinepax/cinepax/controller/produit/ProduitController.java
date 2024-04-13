package eval.cinepax.cinepax.controller.produit;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import eval.cinepax.cinepax.model.film.produit.VChiffreAffaireBProduitJ;
import eval.cinepax.cinepax.service.produit.ProduitService;

@RestController
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    ProduitService produitService;

    @GetMapping("/formVenteProduit")
    public ModelAndView loadFormVenteProduit() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formVenteProduit");
        modelAndView.addObject("listeProduit", produitService.getAllProduit());
        return modelAndView;
    }
    
    @PostMapping("/venteProduit")
    public ModelAndView vendreProduit(
        @RequestParam(name = "id_produit") int idProduit, 
        @RequestParam(name = "date_vente_produit") Date dateVenteProduit, 
        @RequestParam(name = "quantite_vente_produit") int quantiteVenteProduit
    )
    throws Exception {
        produitService.vendreProduit(idProduit, dateVenteProduit, quantiteVenteProduit);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView();
        redirectView.setUrl("/produit/formVenteProduit");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/chiffreAffaireBProduit/{date}")
    public List<VChiffreAffaireBProduitJ> getChiffreAffaireBFilm(@PathVariable(required = false) Date date) {
        if(date==null) {
            date = Date.valueOf(LocalDate.now());
        }
        return produitService.getChiffreAffaireBProduit(date);
    }
}
