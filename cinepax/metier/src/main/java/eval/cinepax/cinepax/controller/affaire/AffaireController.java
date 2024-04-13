package eval.cinepax.cinepax.controller.affaire;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import eval.cinepax.cinepax.service.billet.BilletService;
import eval.cinepax.cinepax.service.produit.ProduitService;

@RestController
@RequestMapping("/affaire")
public class AffaireController {
    @Autowired
    BilletService billetService;
    @Autowired
    ProduitService produitService;

    @GetMapping("/chiffreAffaire")
    public ModelAndView getChiffreAffaireProduit(@RequestParam(required = false, name = "date") Date date) {
        ModelAndView modelAndView=new ModelAndView();  
        if(date==null) {
            date = Date.valueOf(LocalDate.now());
        }
        modelAndView.addObject("ca_film", billetService.getChiffreAffaireTotalBFilm(date));
        modelAndView.addObject("nb_vue_total", billetService.getNbVueTotal(date));
        modelAndView.addObject("ca_produit", produitService.getChiffreAffaireProduitTotal(date));
        modelAndView.addObject("listeCAFilm", billetService.getChiffreAffaireBFilm(date));
        modelAndView.addObject("listeVueFilm", billetService.getStatNbVueDate(date));
        modelAndView.addObject("listeCAProduit", produitService.getChiffreAffaireBProduit(date));
        modelAndView.addObject("date", date);
        modelAndView.setViewName("dynamique/chiffreAffaire");
        return modelAndView;
    }
}
