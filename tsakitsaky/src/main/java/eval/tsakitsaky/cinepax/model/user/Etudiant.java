package eval.tsakitsaky.cinepax.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etudiant")
    int idEtudiant;
    @Column(name = "email_etudiant")
    String emailEtudiant;
    @Column(name = "prenom_etudiant")
    String prenomEtudiant;
    @Column(name = "mdp_etudiant")
    String mdpEtudiant;
    public Etudiant() {
    }
    public int getIdEtudiant() {
        return idEtudiant;
    }
    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
    public String getEmailEtudiant() {
        return emailEtudiant;
    }
    public void setEmailEtudiant(String emailEtudiant)
    throws Exception {
        if(emailEtudiant==null||emailEtudiant.length()==0) {
            throw new Exception("Veuillez entrer un email d'etudiant");
        }
        this.emailEtudiant = emailEtudiant;
    }
    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }
    public void setPrenomEtudiant(String prenomEtudiant)
    throws Exception {
        if(prenomEtudiant==null||prenomEtudiant.length()==0) {
            throw new Exception("Veuillez entrer un prenom d'etudiant");
        }
        this.prenomEtudiant = prenomEtudiant;
    }
    public String getMdpEtudiant() {
        return mdpEtudiant;
    }
    public void setMdpEtudiant(String mdpEtudiant)
    throws Exception {
        if(mdpEtudiant==null||mdpEtudiant.length()==0) {
            throw new Exception("Veuillez entrer un mot de passe d'etudiant");
        }
        this.mdpEtudiant = mdpEtudiant;
    }
}
