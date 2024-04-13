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
@Table(name = "station")
public class Station implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_station")
    int idStation;
    @Column(name = "mail_station")
    String mailStation;
    @Column(name = "mdp_station")
    String mdpStation;
    @Column(name = "role")
    String role;
    public Station() {
    }
    public Station(String mailStation, String mdpStation, String role)
    throws Exception {
        this.setMailStation(mailStation);
        this.setMdpStation(mdpStation);
        this.setRole(role);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new CustomGrantedAuthority("ROLE_"+role));
    }
    @Override
    public String getPassword() {
        return mdpStation;
    }
    @Override
    public String getUsername() {
        return mailStation+","+role;
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
    public int getIdStation() {
        return idStation;
    }
    public void setIdStation(int idStation)
    throws Exception {
        if(idStation<=0) {
            throw new Exception("Veuillez entrer un id de station valide");
        }
        this.idStation = idStation;
    }
    public String getMailStation() {
        return mailStation;
    }
    public void setMailStation(String mailStation)
    throws Exception {
        if(mailStation==null||mailStation.length()==0) {
            throw new Exception("Veuillez entrer un mail de station");
        }
        this.mailStation = mailStation;
    }
    public String getMdpStation() {
        return mdpStation;
    }
    public void setMdpStation(String mdpStation)
    throws Exception {
        if(mdpStation==null||mdpStation.length()==0) {
            throw new Exception("Veuillez entrer un mot de passe de de station");
        }
        this.mdpStation = mdpStation;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role)
    throws Exception {
        if(role==null||role.length()==0) {
            throw new Exception("Veuillez entrer un role de station");
        }
        this.role = role;
    }
}
