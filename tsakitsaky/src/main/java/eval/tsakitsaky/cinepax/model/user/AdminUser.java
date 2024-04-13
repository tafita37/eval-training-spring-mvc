package eval.tsakitsaky.cinepax.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_user")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_user")
    int idAdminUser;
    @Column(name = "email_admin_user")
    String emailAdminUser;
    @Column(name = "prenom_admin_user")
    String prenomAdminUser;
    @Column(name = "mdp_admin_user")
    String mdpAdminUser;
    public AdminUser() {
    }
    public int getIdAdminUser() {
        return idAdminUser;
    }
    public void setIdAdminUser(int idAdminUser) {
        this.idAdminUser = idAdminUser;
    }
    public String getEmailAdminUser() {
        return emailAdminUser;
    }
    public void setEmailAdminUser(String emailAdminUser)
    throws Exception {
        if(emailAdminUser==null||emailAdminUser.length()==0) {
            throw new Exception("Veuillez entrer un email de admin");
        }
        this.emailAdminUser = emailAdminUser;
    }
    public String getPrenomAdminUser() {
        return prenomAdminUser;
    }
    public void setPrenomAdminUser(String prenomAdminUser)
    throws Exception {
        if(prenomAdminUser==null||prenomAdminUser.length()==0) {
            throw new Exception("Veuillez entrer un prenom de admin");
        }
        this.prenomAdminUser = prenomAdminUser;
    }
    public String getMdpAdminUser() {
        return mdpAdminUser;
    }
    public void setMdpAdminUser(String mdpAdminUser)
    throws Exception {
        if(mdpAdminUser==null||mdpAdminUser.length()==0) {
            throw new Exception("Veuillez entrer un mdp de admin");
        }
        this.mdpAdminUser = mdpAdminUser;
    }
}
