package eval.mikolo.mikolo.model.profile;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import eval.mikolo.mikolo.config.CustomGrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    int idAdmin;
    @Column(name = "mail_admin")
    String mailAdmin;
    @Column(name = "mdp_admin")
    String mdpAdmin;
    @Column(name = "role")
    String role;
    public Admin() {
    }
    public Admin(String mailAdmin, String mdpAdmin, String role)
    throws Exception {
        this.setMailAdmin(mailAdmin);
        this.setMdpAdmin(mdpAdmin);
        this.setRole(role);
    }
    public int getIdAdmin() {
        return idAdmin;
    }
    public void setIdAdmin(int idAdmin)
    throws Exception {
        if(idAdmin<=0) {
            throw new Exception("Veuillez entrer un id de admin valide");
        }
        this.idAdmin = idAdmin;
    }
    public String getMailAdmin() {
        return mailAdmin;
    }
    public void setMailAdmin(String mailAdmin)
    throws Exception {
        if(mailAdmin==null||mailAdmin.length()==0) {
            throw new Exception("Veuillez entrer un mail de admin");
        }
        this.mailAdmin = mailAdmin;
    }
    public String getMdpAdmin() {
        return mdpAdmin;
    }
    public void setMdpAdmin(String mdpAdmin)
    throws Exception {
        if(mdpAdmin==null||mdpAdmin.length()==0) {
            throw new Exception("Veuillez entrer un mot de passe de admin");
        }
        this.mdpAdmin = mdpAdmin;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role)
    throws Exception {
        if(role==null||role.length()==0) {
            throw new Exception("Veuillez entrer un role de admin");
        }
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new CustomGrantedAuthority(role));
    }
    @Override
    public String getPassword() {
        return mdpAdmin;
    }
    @Override
    public String getUsername() {
        return mailAdmin+","+role;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
