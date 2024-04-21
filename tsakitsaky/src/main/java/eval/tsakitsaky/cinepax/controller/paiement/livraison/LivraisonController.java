package eval.tsakitsaky.cinepax.controller.paiement.livraison;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Locale;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xhtmlrenderer.pdf.ITextRenderer;

import eval.tsakitsaky.cinepax.Constante;
import eval.tsakitsaky.cinepax.model.paiement.livraison.VEtatLivraison;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xhtmlrenderer.pdf.ITextRenderer;

import eval.tsakitsaky.cinepax.Constante;
import eval.tsakitsaky.cinepax.service.paiement.livraison.LivraisonService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/livraison")
public class LivraisonController {
    @Value("${app.baseUrl}")
    private String baseUrl;
    @Autowired
    LivraisonService livraisonService;

    @GetMapping("/etatLivraison")
    public ModelAndView getEtatLivraison(@RequestParam(name = "idAxe", required = false, defaultValue = "0") int idAxe) {
        ModelAndView modelAndView=new ModelAndView("dynamique/listeEtatLivraison");
        modelAndView.addObject("listeEtat", livraisonService.getAllEtatLivraison());
        return modelAndView;
    }

    @GetMapping("/exportPdf")
    public void exportPdf(HttpServletResponse response) throws Exception {
        Locale locale = new Locale("fr", "FR");
        NumberFormat formatteur = NumberFormat.getInstance(locale);
        List<VEtatLivraison> listVEtatLivraison = livraisonService.getAllEtatLivraison();
        String htmlContain="";
        for(int i=0; i<listVEtatLivraison.size(); i++) {
            htmlContain+="<tr>";
            htmlContain+="<td>"+listVEtatLivraison.get(i).getAxe().getQuartierConcatener()+"</td>";
            htmlContain+="<td>"+listVEtatLivraison.get(i).getClient().getNomClient()+"</td>";
            htmlContain+="<td>"+listVEtatLivraison.get(i).getClient().getContactClient()+"</td>";
            htmlContain+="<td>"+listVEtatLivraison.get(i).getEtudiant().getPrenomEtudiant()+"</td>";
            htmlContain+="<td>"+formatteur.format(listVEtatLivraison.get(i).getNbPack())+"</td>";
            htmlContain+="<td>"+formatteur.format(listVEtatLivraison.get(i).getMontantTotal())+" ar</td>";
            htmlContain+="<td>"+listVEtatLivraison.get(i).getDateVenteBillet()+"</td>";
            htmlContain+="</tr>";
        }
        String contenu="<table class=\"custom-table\" border=\"1\">"+
                            "<thead>"+
                                "<tr>"+
                                    "<th>Axe</th>"+
                                    "<th>Client</th>"+
                                    "<th>Contact</th>"+
                                    "<th>Vendeur</th>"+
                                    "<th>Nombre de Pack</th>"+
                                    "<th>Montant total</th>"+
                                    "<th>Date</th>"+
                                "</tr>"+
                            "</thead>"+
                            "<tbody>"+
                                htmlContain+
                            "</tbody>"+
                        "</table>";
        String htmlContent=Constante.getHTMLPDFTemplate(contenu, baseUrl, 1000, "Liste des livraisons");
        System.out.println(htmlContent);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        renderer.createPDF(outputStream);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.pdf\"");
        OutputStream responseOutputStream = response.getOutputStream();
        outputStream.writeTo(responseOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
