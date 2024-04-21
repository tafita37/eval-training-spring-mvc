package eval.cinepax.cinepax.controllerAdvice;
// package eval.cinepax.cinepax.controllerAdvice;

import java.util.Hashtable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import eval.cinepax.cinepax.exception.ExcelException;


@ControllerAdvice
public class GlobalExceptionHandler {
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<Hashtable<String, Object>> handleException(Exception ex) {
//         Hashtable<String, Object> result = new Hashtable<>();
//         result.put("message", ex.getMessage().replaceAll("JSON parse error: ", ""));
//         int status=HttpStatus.INTERNAL_SERVER_ERROR.value();
//         // if(ex instanceof CatchingException) {
//         //     status=((CatchingException) ex).getStatus();
//         // }
//         ex.printStackTrace();
//         return ResponseEntity.status(status).body(result);
//     }

    @ExceptionHandler(ExcelException.class)
    public ModelAndView getErrorMessage(ExcelException excelException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("excelError", excelException);
        RedirectView redirectView=new RedirectView("/billet/formNewBillet");
        ModelAndView modelAndView=new ModelAndView(redirectView);
        modelAndView.addObject("excelError", excelException);
        excelException.printStackTrace();
        return modelAndView;
    }
}