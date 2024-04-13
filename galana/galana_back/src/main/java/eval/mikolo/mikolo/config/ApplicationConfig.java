package eval.mikolo.mikolo.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import eval.mikolo.mikolo.model.profile.Admin;
import eval.mikolo.mikolo.model.profile.Station;
import eval.mikolo.mikolo.repository.profile.LoginRepository;

@Configuration
public class ApplicationConfig {
    @Autowired
    LoginRepository loginRepository;
    
    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            String mail=username.split(",")[0];
            String role=username.split(",")[1];
            Optional<Admin> optionalAdmin=loginRepository.findAdminByMailAndRole(mail, role);
            if(optionalAdmin.isPresent()) {
                return optionalAdmin.get();
            } else {
                Optional<Station> optionalStation=loginRepository.findStationByMailAndRole(mail, role);
                return optionalStation.orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder()  ;
    }

}
