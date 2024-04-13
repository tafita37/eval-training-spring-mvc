package eval.mikolo.mikolo.service.authentification;

import java.util.Hashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import eval.mikolo.mikolo.config.AuthenticationRequest;
import eval.mikolo.mikolo.config.AuthenticationResponse;
import eval.mikolo.mikolo.config.JwtService;
import eval.mikolo.mikolo.config.RegisterRequest;
import eval.mikolo.mikolo.model.profile.Admin;
import eval.mikolo.mikolo.model.profile.Station;
import eval.mikolo.mikolo.repository.profile.LoginRepository;

@Service
public class AuthenticationService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired 
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;

    public ResponseEntity<Hashtable <String,Object>> registerStation(RegisterRequest request) {
        Hashtable <String,Object> registerResponse = new Hashtable<>();
        try {
            Station station=new Station(request.getMail(), passwordEncoder.encode(request.getPassword()), "STATION");
            loginRepository.saveStation(station);
            var jwtToken = jwtService.generateToken(station);
            AuthenticationResponse tokenObj =AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
            registerResponse.put("status" , 200);
            registerResponse.put("message", "Compte de station creer !");
            registerResponse.put("data", tokenObj);
        } catch (Exception e) {
            registerResponse.put("status" , 500);
            registerResponse.put("message", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(registerResponse);
    } 

    public ResponseEntity<Hashtable <String,Object>> registerAdmin(RegisterRequest request) {
        Hashtable <String,Object> registerResponse = new Hashtable<>();
        try {
            Admin admin=new Admin(request.getMail(), passwordEncoder.encode(request.getPassword()), "ADMIN");
            loginRepository.saveAdmin(admin);
            var jwtToken = jwtService.generateToken(admin);
            AuthenticationResponse tokenObj =AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
            registerResponse.put("status" , 200);
            registerResponse.put("message", "Compte de admin creer !");
            registerResponse.put("data", tokenObj);
        } catch (Exception e) {
            registerResponse.put("status" , 500);
            registerResponse.put("message", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(registerResponse);
    } 

    public ResponseEntity<Hashtable <String,Object>> authenticateStation(AuthenticationRequest authenticationRequest){
        Hashtable <String,Object> authenticationResponse = new Hashtable<>();
        try {
            Station station=new Station(authenticationRequest.getMail(), authenticationRequest.getMdp(), "STATION");
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(station.getUsername(), station.getPassword())
            );
            var user = loginRepository.findStationByMail(station.getMailStation()).orElseThrow(); 
            String jwtToken = jwtService.generateToken(user);
            AuthenticationResponse tokenObj = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
            authenticationResponse.put("status", 200);
            authenticationResponse.put("message", "Vous etes connectée");
            authenticationResponse.put("data", tokenObj);
            return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
        } 
        catch (Exception e) {
            e.printStackTrace();
            authenticationResponse.put("status", HttpStatus.FORBIDDEN.value());
            authenticationResponse.put("message", "Mot de passe ou mail incorectes !");
            authenticationResponse.put("data", "no data");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authenticationResponse);
        }
    }

    public ResponseEntity<Hashtable <String,Object>> authenticateAdmin(AuthenticationRequest authenticationRequest){
        Hashtable <String,Object> authenticationResponse = new Hashtable<>();
        try {
            Admin admin=new Admin(authenticationRequest.getMail(), authenticationRequest.getMdp(), "ADMIN");
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword())
            );
            var user = loginRepository.findAdminByMail(admin.getMailAdmin()).orElseThrow(); 
            String jwtToken = jwtService.generateToken(user);
            AuthenticationResponse tokenObj = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
            authenticationResponse.put("status", 200);
            authenticationResponse.put("message", "Vous etes connectée");
            authenticationResponse.put("data", tokenObj);
            return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
        } 
        catch (Exception e) {
            e.printStackTrace();
            authenticationResponse.put("status", 400);
            authenticationResponse.put("message", "Mot de passe ou mail incorectes !");
            authenticationResponse.put("data", "no data");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authenticationResponse);
        }
    }
}