package eval.tsakitsaky.cinepax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BeginController {
    @GetMapping("/")
    public ModelAndView entryPoint() {
        ModelAndView modelAndView = new ModelAndView();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/loginInscription/formLoginEtudiant");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dynamique/test");
        return modelAndView;
    }
}
