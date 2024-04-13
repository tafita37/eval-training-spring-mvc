package eval.mikolo.mikolo.controller.profile;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eval.mikolo.mikolo.config.AuthenticationRequest;
import eval.mikolo.mikolo.config.RegisterRequest;
import eval.mikolo.mikolo.service.authentification.AuthenticationService;
import eval.mikolo.mikolo.service.profile.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/station/registerStation")
    public ResponseEntity<Hashtable<String, Object>> insertStation(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.registerStation(registerRequest);
    }
    
    @PostMapping("/admin/registerAdmin")
    public ResponseEntity<Hashtable<String, Object>> insertAdmin(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.registerAdmin(registerRequest);
    }
    
    @PostMapping("/admin/loginAdmin")
    public ResponseEntity<Hashtable<String, Object>> loginAdmin(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticateAdmin(authenticationRequest);
    }
    
    @PostMapping("/station/loginStation")
    public ResponseEntity<Hashtable<String, Object>> loginStation(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticateStation(authenticationRequest);
    }
}
