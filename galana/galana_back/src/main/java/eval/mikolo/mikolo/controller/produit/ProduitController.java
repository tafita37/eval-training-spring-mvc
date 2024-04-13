package eval.mikolo.mikolo.controller.produit;

import java.util.Hashtable;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eval.mikolo.mikolo.model.autre.ChoixVente;
import eval.mikolo.mikolo.model.produit.MouvementProduit;
import eval.mikolo.mikolo.model.produit.Produit;
import eval.mikolo.mikolo.service.produit.ProduitService;

@RestController
@RequestMapping("/produit")
@CrossOrigin("*")
public class ProduitController {
    @Autowired
    ProduitService produitService;

    @PostMapping("/ajoutStock")
    public ResponseEntity<Hashtable<String, Object>> ajoutStockProduit(
        @RequestHeader Map<String, String> headers,
        @RequestBody MouvementProduit mouvementProduit
    )
    throws Exception {
        String token=headers.get("authorization").substring(7);
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Stock du produit ajouter");
        produitService.newMouvement(mouvementProduit, 0, token);
        result.put("status", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/insertProduit")
    public ResponseEntity<Hashtable<String, Object>> insertProduit(@RequestBody Produit produit)
    throws Exception {
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Produit creer");
        produitService.saveProduit(produit);
        result.put("status", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/vendreProduit")
    public ResponseEntity<Hashtable<String, Object>> vendreProduit(
        @RequestHeader Map<String, String> headers,
        @RequestBody ChoixVente choixVente
    )
    throws Exception {
        String token=headers.get("authorization").substring(7);
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Produit vendu");
        produitService.vendreProduit(choixVente, token);
        result.put("status", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/allProduit")
    public ResponseEntity<Hashtable<String, Object>> getAllProduit() {
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Produits recuperees");
        result.put("data", produitService.findAllProduit());
        result.put("status", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/allProduit/{numPage}")
    public ResponseEntity<Hashtable<String, Object>> getAllProduit(@PathVariable int numPage) {
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Produits recuperees");
        result.put("data", produitService.findAllProduit(numPage));
        result.put("status", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/countNbPageProduit")
    public ResponseEntity<Hashtable<String, Object>> countNbPageProduit() {
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Nombre de page de produit recuperee");
        result.put("data", produitService.countNbPageProduit());
        result.put("status", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/countNbPageEntreeStock")
    public ResponseEntity<Hashtable<String, Object>> countNbPageEntreeStock(@RequestHeader Map<String, String> headers) {
        String token=headers.get("authorization").substring(7);
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Nombre de page d'ajout de stock recuperee");
        result.put("data", produitService.countNbPageEntreeStock(token));
        result.put("status", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/listeEntreeStock/{numPage}")
    public ResponseEntity<Hashtable<String, Object>> getListeEntreeStock(
        @RequestHeader Map<String, String> headers, 
        @PathVariable int numPage
    ) {
        String token=headers.get("authorization").substring(7);
        Hashtable<String, Object> result=new Hashtable<>();
        result.put("message", "Nombre de page de produit recuperee");
        result.put("data", produitService.getListeEntree(token, numPage));
        result.put("status", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
