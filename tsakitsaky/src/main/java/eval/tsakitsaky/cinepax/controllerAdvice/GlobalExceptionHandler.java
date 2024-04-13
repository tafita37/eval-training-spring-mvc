package eval.tsakitsaky.cinepax.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import eval.tsakitsaky.cinepax.config.UnauthorizedAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView loadErrorMessage(NoResourceFoundException ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/error/error404");
        modelAndView.addObject("errorMessage", ex.getMessage());
        ex.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ModelAndView loadErrorMessage(UnauthorizedAccessException ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/error/error403");
        modelAndView.addObject("errorMessage", ex.getMessage());
        ex.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView loadErrorMessage(Exception ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/error/errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        ex.printStackTrace();
        return modelAndView;
    }
}