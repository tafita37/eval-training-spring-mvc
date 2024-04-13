package eval.tsakitsaky.cinepax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import eval.tsakitsaky.cinepax.model.user.AdminUser;
import eval.tsakitsaky.cinepax.model.user.Etudiant;
import eval.tsakitsaky.cinepax.service.user.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/loginInscription")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/formLoginEtudiant")
    public ModelAndView getFormLoginEtudiant() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/loginEtudiant");
        return modelAndView;
    }

    @GetMapping("/formLoginAdminUser")
    public ModelAndView getFormLoginAdminUser() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/loginAdminUser");
        return modelAndView;
    }

    @PostMapping("/loginEtudiant")
    public ModelAndView loginEtudiant(
        @RequestParam("emailEtudiant") String emailEtudiant, 
        @RequestParam("mdpEtudiant") String mdpEtudiant,
        HttpSession httpSession
    )
    throws Exception {
        Etudiant etudiant=userService.loginEtudiant(emailEtudiant.trim(), mdpEtudiant.trim());
        httpSession.setAttribute("role", "etudiant");
        httpSession.setAttribute("etudiant", etudiant);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView();
        redirectView.setUrl("/ventePack/formVentePack");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @PostMapping("/loginAdminUser")
    public String loginAdminUser(
        @RequestParam("emailAdminUser") String emailAdminUser, 
        @RequestParam("mdpAdminUser") String mdpAdminUser,
        HttpSession httpSession
    ) {
        AdminUser adminUser=userService.loginAdminUser(emailAdminUser.trim(), mdpAdminUser.trim());
        httpSession.setAttribute("role", "ROLE_ADMIN");
        httpSession.setAttribute("adminUser", adminUser);
        if(httpSession.getAttribute("adminUser")==null) {
            return "Email ou mot de passe errone";
        }
        return "Connecte";
    }
}
