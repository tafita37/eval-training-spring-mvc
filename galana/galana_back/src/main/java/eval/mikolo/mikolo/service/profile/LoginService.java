package eval.mikolo.mikolo.service.profile;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import eval.mikolo.mikolo.config.JwtService;
import eval.mikolo.mikolo.model.profile.Admin;
import eval.mikolo.mikolo.model.profile.Station;
import eval.mikolo.mikolo.repository.profile.LoginRepository;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    JwtService jwtService;
    @Autowired 
    UserDetailsService userDetailsService;

    public Optional<Station> loginStation(String mail, String mdp) {
        return loginRepository.loginStation(mail, mdp);
    }

    public Optional<Admin> loginAdmin(String mail, String mdp) {
        return loginRepository.loginAdmin(mail, mdp);
    }

    public Station findStationConnectedByToken(String token) {
        String userName=jwtService.extractUserMail(token);
        return (Station) userDetailsService.loadUserByUsername(userName);
    }
}
