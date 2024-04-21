package eval.cinepax.cinepax.model.tmpClass;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import eval.cinepax.cinepax.Constante;
import eval.cinepax.cinepax.exception.ColumnExcelException;
import eval.cinepax.cinepax.exception.ExcelException;
import eval.cinepax.cinepax.exception.LineExcelException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tmpTable")
public class TmpClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "num_seance")
    int numSeance;
    @Column(name = "film")
    String film;
    @Column(name = "salle")
    String salle;
    @Column(name = "date_heure_seance")
    Timestamp dateHeureSeance;
    public TmpClass(Row row) throws LineExcelException {
        String exceptionMessage="";
        try {
            this.setNumSeance(row, 0);
        } catch (ColumnExcelException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setFilm(row, 1);
        } catch (ColumnExcelException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setSalle(row, 2);
        } catch (ColumnExcelException e) {
            exceptionMessage+=e.getMessage();
        }

        Date dateColumn=null;
        try {
            dateColumn=Constante.dateUtilToDateSql(row.getCell(3).getDateCellValue());
        } catch (Exception e) {
            exceptionMessage+="colonne number 3 : "+e.getMessage();
        }
        Time timeColumn = null;
        try {
            timeColumn = Constante.dateUtilToTimeSql(row.getCell(4).getDateCellValue());
        } catch (Exception e) {
            exceptionMessage+="colonne number 4 : "+e.getMessage();
        }

        try {
            this.setDateHeureSeance(Timestamp.valueOf(dateColumn.toString() + " " + timeColumn.toString()));
        } catch (Exception e) {
            exceptionMessage+=e.getMessage();
        }
        
        if(exceptionMessage.length()!=0) {
            throw new LineExcelException(exceptionMessage, row.getRowNum());
        }
    }
    public TmpClass() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumSeance() {
        return numSeance;
    }
    public void setNumSeance(int numSeance)
    throws Exception {
        if(numSeance<=0) {
            throw new Exception("Le numero de seance est trop petit");
        }
        this.numSeance = numSeance;
    }

    public void setNumSeance(Row row, int colonneNumber) 
    throws ColumnExcelException {
        ColumnExcelException columnExcelException;
        try {
            this.setNumSeance((int) row.getCell(colonneNumber).getNumericCellValue());
        } catch (Exception e) {
            columnExcelException=new ColumnExcelException(e.getMessage(), colonneNumber);
            throw columnExcelException;
        }
    }
    public String getFilm() {
        return film;
    }
    public void setFilm(String film)
    throws Exception {
        film=film.trim();
        if(film==null||film.length()==0) {
            throw new Exception("Veuillez entrer un nom de film");
        }
        this.film = film;
    }
    public void setFilm(Row row, int colonneNumber) throws ColumnExcelException {
        ColumnExcelException columnExcelException;
        try {
            this.setFilm(row.getCell(colonneNumber).getStringCellValue());
        } catch (Exception e) {
            columnExcelException=new ColumnExcelException(e.getMessage(), colonneNumber);
            throw columnExcelException;
        }
    }
    public String getSalle() {
        return salle;
    }
    public void setSalle(String salle)
    throws Exception {
        salle=salle.trim();
        if(salle==null||salle.length()==0) {
            throw new Exception("Veuillez entrer un nom de salle");
        }
        this.salle = salle;
    }
    public void setSalle(Row row, int colonneNumber) throws ColumnExcelException {
        try {
            this.setSalle(row.getCell(colonneNumber).getStringCellValue());
        } catch (Exception e) {
            throw new ColumnExcelException(e.getMessage(), colonneNumber);
        }
    }
    public Timestamp getDateHeureSeance() {
        return dateHeureSeance;
    }
    public void setDateHeureSeance(Timestamp dateHeureSeance)
    throws Exception {
        if(dateHeureSeance==null) {
            throw new Exception("Veuillez entrer une date et heure de seance");
        }
        this.dateHeureSeance = dateHeureSeance;
    }

    public static TmpClass[] getListeExcelImport(Sheet sheet) throws ExcelException {
        ExcelException excelException=new ExcelException();
        TmpClass[] result=new TmpClass[sheet.getLastRowNum()];
        for(int i=1; i<=sheet.getLastRowNum(); i++) {
            try {
                result[i-1]=new TmpClass(sheet.getRow(i));
            } catch (LineExcelException e) {
                excelException.getListeLineException().add(e);
            }
        }
        if(excelException.getListeLineException().size()!=0) {
            throw excelException;
        }
        return result;
    }
}
