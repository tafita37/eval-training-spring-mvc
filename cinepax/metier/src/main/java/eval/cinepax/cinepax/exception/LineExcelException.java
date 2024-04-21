package eval.cinepax.cinepax.exception;

public class LineExcelException extends Exception {
    int lineNumber;

    public LineExcelException(String arg0, int lineNumber) {
        super("Error ("+arg0+") in line "+lineNumber);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
