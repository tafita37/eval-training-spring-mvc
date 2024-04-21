package eval.tsakitsaky.cinepax.model.axe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    int idClient;
    @Column(name = "nom_client")
    String nomClient;
    @Column(name = "contact_client")
    String contactClient;
    @ManyToOne
    @JoinColumn(name = "id_axe")
    Axe axe;
    public Client() {
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient)
    throws Exception {
        if(nomClient==null||nomClient.length()==0) {
            throw new Exception("Veuillez entrer un nom de client");
        }
        this.nomClient = nomClient;
    }
    public String getContactClient() {
        return contactClient;
    }
    public void setContactClient(String contactClient)
    throws Exception {
        if(contactClient==null||contactClient.length()==0) {
            throw new Exception("Veuillez entrer un contact de client");
        }
        this.contactClient = contactClient;
    }
    public Axe getAxe() {
        return axe;
    }
    public void setAxe(Axe axe)
    throws Exception {
        if(axe==null) {
            throw new Exception("Veuillez entrer un axe de client");
        }
        this.axe = axe;
    }

    public String getNomAfficher() {
        return this.getNomClient()+" "+this.getAxe().getNomAfficher();
    }
}
