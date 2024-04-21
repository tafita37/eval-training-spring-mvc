package eval.cinepax.cinepax.controller.billet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import org.xhtmlrenderer.pdf.ITextRenderer;
import eval.cinepax.cinepax.Constante;
import eval.cinepax.cinepax.exception.ExcelException;
import eval.cinepax.cinepax.model.film.Billet;
import eval.cinepax.cinepax.model.film.Film;
import eval.cinepax.cinepax.model.film.Salle;
import eval.cinepax.cinepax.model.film.VChiffreAffaireBFilmJ;
import eval.cinepax.cinepax.model.film.VNbVueBFilmJ;
import eval.cinepax.cinepax.model.place.Place;
import eval.cinepax.cinepax.model.tmpClass.TmpClass;
import eval.cinepax.cinepax.model.vente.PlaceVenteBillet;
import eval.cinepax.cinepax.model.vente.VenteBillet;
import eval.cinepax.cinepax.service.billet.BilletService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/billet")
public class BilletController {
    @Autowired
    BilletService billetService;
    @Value("${app.baseUrl}")
    private String baseUrl;

    @GetMapping("")
    public ModelAndView loadForm() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formVenteBillet");
        String[] cssList={"formVenteBillet.css"};
        modelAndView.addObject("cssList", cssList);
        modelAndView.addObject("listeBillet", billetService.getAllBillet());
        modelAndView.addObject("listePlace", billetService.getAllPlace());
        return modelAndView;
    }

    @GetMapping("/formNewBillet")
    public ModelAndView loadFormNewBillet() {
        ModelAndView modelAndView=new ModelAndView();
        ExcelException excelError = (ExcelException) modelAndView.getModel().get("excelError");
        System.out.println(excelError);
        modelAndView.setViewName("dynamique/formNewBillet");
        modelAndView.addObject("listeFilm", billetService.getAllFilm());
        modelAndView.addObject("listeSalle", billetService.getAllSalle());
        return modelAndView;
    }

    @PostMapping("/creerBillet")
    public ModelAndView creerBillet(
        @RequestParam(name = "id_film", required = false, defaultValue = "0") int idFilm, 
        @RequestParam(name = "id_salle", required = false, defaultValue = "0") int idSalle, 
        @RequestParam(name = "date_heure_billet", required = false) String dateHeureBillet
    )
    throws Exception {
        Billet billet=new Billet(new Film(idFilm), new Salle(idSalle), Constante.stringToTimestamp(dateHeureBillet));
        billetService.insertBillet(billet);
        ModelAndView modelAndView = new ModelAndView();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/billet/formNewBillet");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @PostMapping("/vendreBillet")
    public ModelAndView vendreBillet(
        @RequestParam("id_billet") int idBillet, 
        @RequestParam(name = "id_place_enfant", required = false) String[] listePlaceEnfant, 
        @RequestParam(name = "id_place_adulte", required = false) String[] listePlaceAdulte, 
        @RequestParam("date_vente_billet") Date dateVenteBillet
    )
    throws Exception {
        if((listePlaceEnfant==null||listePlaceEnfant.length==0)&&(listePlaceAdulte==null||listePlaceAdulte.length==0)) {
            throw new Exception("Veuillez entrer une liste de place");
        }
        int sizeEnfant=0;
        int sizeAdulte=0;
        if(listePlaceEnfant!=null&&listePlaceEnfant.length!=0) {
            sizeEnfant=listePlaceEnfant.length;
        }
        if(listePlaceAdulte!=null&&listePlaceAdulte.length!=0) {
            sizeAdulte=listePlaceAdulte.length;
        }
        VenteBillet venteBillet=new VenteBillet(billetService.findBilletById(idBillet), dateVenteBillet);
        String[] listePlace=new String[sizeEnfant+sizeAdulte];
        PlaceVenteBillet[] listePlaceVenteBillets=new PlaceVenteBillet[sizeEnfant+sizeAdulte];
        if(listePlaceEnfant!=null) {
            System.arraycopy(listePlaceEnfant, 0, listePlace, 0, sizeEnfant);
        }
        if(listePlaceAdulte!=null) {
            System.arraycopy(listePlaceAdulte, 0, listePlace, sizeEnfant, sizeAdulte);
        }
        Place place=null;
        int adulteEnfant=11;
        for(int i=0; i<listePlace.length; i++) {
            adulteEnfant=11;
            place=billetService.findPlaceById(Integer.valueOf(listePlace[i]));
            if(i>=sizeEnfant) {
                adulteEnfant=0;
            }
            listePlaceVenteBillets[i]=new PlaceVenteBillet(place);
            listePlaceVenteBillets[i].setPlaceAdulteEnfant(adulteEnfant);
        }
        billetService.vendreBillet(venteBillet, listePlaceVenteBillets);
        ModelAndView modelAndView = new ModelAndView();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/getStatistiqueVenteBFilm")
    public ModelAndView getStatVenteByFilm() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/statVenteFilm");
        modelAndView.addObject("listeStatVente", billetService.getStatVenteFilm());
        return modelAndView;
    }

    @GetMapping("/place/listePlaceDispoBySeance")
    public ModelAndView getListePlaceDispo(@RequestParam(defaultValue = "0", name = "id_billet") int idBillet)
    throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listePlaceDispo");
        modelAndView.addObject("listeCountRangee", billetService.countPlaceByRangee(idBillet));
        modelAndView.addObject("listeBillet", billetService.getAllBillet());
        return modelAndView;
    }

    @GetMapping("/chiffreAffaireBFilm/{date}")
    public List<VChiffreAffaireBFilmJ> getChiffreAffaireBFilm(@PathVariable(required = false) Date date) {
        if(date==null) {
            date = Date.valueOf(LocalDate.now());
        }
        return billetService.getChiffreAffaireBFilm(date);
    }

    @GetMapping("/nbVueFilm/{date}")
    public List<VNbVueBFilmJ> getStatNbVueDate(@PathVariable(required = false) Date date) {
        if(date==null) {
            date = Date.valueOf(LocalDate.now());
        }
        return billetService.getStatNbVueDate(date);
    }

    @GetMapping("/listeVenteBillet")
    public ModelAndView getListeVenteBillet(
        @RequestParam(required = false, name = "id_billet", defaultValue = "0") int idBillet
    ) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listeVenteBillet");
        modelAndView.addObject("listeBillet", billetService.getAllBillet());
        modelAndView.addObject("listeVenteBillet", billetService.getListeVenteBilletOfBillet(idBillet));
        return modelAndView;
    }

    @GetMapping("/listeTicketOfVente/{idVenteBillet}")
    public ModelAndView getListeTicketOfVente(@PathVariable int idVenteBillet) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listeTicket");
        modelAndView.addObject("listeTicket", billetService.getListePlaceVenteBilletOfVenteBillet(idVenteBillet));
        return modelAndView;
    }

    @GetMapping("/exportPdf/{idPlaceVenteBillet}")
    public void exportPdf( @PathVariable int idPlaceVenteBillet, HttpServletResponse response) throws Exception {
        PlaceVenteBillet placeVenteBillet=billetService.findPlaceVenteBilletById(idPlaceVenteBillet);
        // Créez une chaîne HTML que vous souhaitez convertir en PDF
        Locale locale = new Locale("fr", "FR");
        NumberFormat formatteur = NumberFormat.getInstance(locale);
        String contenu="<h4>Cinepax</h4>"+
                        "<div style=\"width: 1000px;height: 100px;\">"+
                            "<span style=\"width : 75px; display : block; float:right; margin-right:500px;\">"+
                                "<p>"+
                                    "Salle : "+
                                "</p>"+
                                "<p>"+
                                    placeVenteBillet.getVenteBillet().getBillet().getSalle().getNomSalle()+
                                "</p>"+
                            "</span>"+
                            "<span style=\"width : 75px; display : block; float:right; margin-right:350px;\">"+
                                "<p>Film : </p>"+
                                "<p>"+
                                    placeVenteBillet.getVenteBillet().getBillet().getFilm().getNomFilm()+
                                "</p>"+
                            "</span>"+
                        "</div>"+
                        "<div style=\"width: 1000px;height: 100px;\">"+
                            "<span style=\"margin-right: 100px\">"+
                                "Date: "+ 
                                placeVenteBillet.getVenteBillet().getBillet().getDateBillet()+
                            "</span>"+
                            "<span style=\"margin-right: 100px\">"+
                                "Time: "+
                                placeVenteBillet.getVenteBillet().getBillet().getHoursBillet()+
                            "</span>"+
                            "<span style=\"margin-right: 100px\">"+
                                "Seat: "+
                                placeVenteBillet.getPlace().getNumeroPlace()+
                            "</span>"+
                            "<span style=\"margin-right: 100px\">"+
                                "Row: "+
                                placeVenteBillet.getPlace().getRangee().getNomRangee()+
                            "</span>"+
                        "</div>"+
                        "<div style=\"width: 1000px;height: 100px;\">"+
                            "<span style=\"width : 75px; display : block; float:right; margin-right:500px;\">"+
                                "<p>"+
                                    "Prix :"+ 
                                "</p>"+
                                "<p>"+
                                    formatteur.format(placeVenteBillet.getTarif().getPrixTarif())+" ar"+
                                "</p>"+
                            "</span>"+
                            "<span style=\"width : 75px; display : block;  \">"+
                                "<p>"+
                                    "Siege : "+
                                "</p>"+
                                "<p>"+
                                    placeVenteBillet.getTarif().getCategorieName()+
                                "</p>"+
                            "</span>"+
                        "</div>";
        String htmlContent=Constante.getHTMLPDFTemplate(contenu, baseUrl, 1000);
        System.out.println(htmlContent);
        // Créez un rendu pour le contenu HTML
        ITextRenderer renderer = new ITextRenderer();

        // Chargez le contenu HTML à partir d'une source, par exemple une chaîne de caractères
        renderer.setDocumentFromString(htmlContent);

        // Appliquez les mises en page
        renderer.layout();

        // Créer un flux de sortie pour le PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Générer le PDF
        renderer.createPDF(outputStream);

        // Définir les en-têtes pour la réponse HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.pdf\"");

        // Écrire le PDF dans la réponse
        OutputStream responseOutputStream = response.getOutputStream();
        outputStream.writeTo(responseOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/importSeanceExcel")
    public ModelAndView importExcel(@RequestParam("file") MultipartFile file) 
    throws EncryptedDocumentException, IOException, ExcelException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        TmpClass[] listeTmpClass=TmpClass.getListeExcelImport(sheet);
        billetService.saveListeTmpClass(listeTmpClass);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/billet/formNewBillet");
        modelAndView.setView(redirectView);
        return modelAndView;
    }
}
