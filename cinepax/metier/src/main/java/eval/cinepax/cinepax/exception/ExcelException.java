package eval.cinepax.cinepax.exception;

import java.util.ArrayList;

public class ExcelException extends Exception {
    ArrayList<LineExcelException> listeLineException=new ArrayList<>();

    public ArrayList<LineExcelException> getListeLineException() {
        return listeLineException;
    }

    public void setListeLineException(ArrayList<LineExcelException> listeLineException) {
        this.listeLineException = listeLineException;
    }
}
