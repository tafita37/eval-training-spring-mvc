package eval.cinepax.cinepax.exception;

public class ColumnExcelException extends Exception {
    int colonneNumber;

    public ColumnExcelException(String arg0, int colonneNumber) {
        super("colonne number "+(colonneNumber+1)+" : "+arg0);
        this.colonneNumber = colonneNumber;
    }

    public int getColonneNumber() {
        return colonneNumber;
    }

    public void setColonneNumber(int colonneNumber) {
        this.colonneNumber = colonneNumber;
    }
}
