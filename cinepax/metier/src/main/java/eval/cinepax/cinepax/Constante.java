package eval.cinepax.cinepax;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Constante {
    public static Time toTimeOfTimestamp(Timestamp timestamp) {
        long timeInMillis = timestamp.getTime();
        Time time = new Time(timeInMillis);
        return time;
    }

    public static Timestamp stringToTimestamp(String timestampString)
    throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        java.util.Date parsedDate = sdf.parse(timestampString);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        return timestamp;
    }

    public static String getHeureMinuteOfTimestamp(Timestamp timestamp) {
        return timestamp.getHours()+":"+timestamp.getMinutes();
    }

    public static String getDateOfTimestamp(Timestamp timestamp) {
        String annee = String.valueOf(timestamp.getYear() + 1900);
        String mois = String.valueOf(timestamp.getMonth() + 1) ; 
        String jour = String.valueOf(timestamp.getDate());
        while(annee.length()!=4) {
            annee="0"+annee;
        }
        while(mois.length()!=2) {
            mois="0"+mois;
        }
        while(jour.length()!=2) {
            jour="0"+jour;
        }
        return annee+"-"+mois+"-"+jour;
    }

    public static String getHTMLPDFTemplate(String contenu, String baseUrl, double widthSize) {
        return "<!DOCTYPE html>"+
                "<html lang=\"en\">"+
                    "<head>"+
                        "<meta charset=\"UTF-8\"/>"+
                        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>"+
                        "<title>Skydash Admin</title>"+
                    "</head>"+
                    "<body>"+
                        "<div  style=\"width : "+widthSize+"px;\">"+
                            contenu+
                        "</div>"+
                    "</body>"+
                "</html>";
    }

    public static Date dateUtilToDateSql(java.util.Date dateUtil) {
        return new Date(dateUtil.getTime());
    }

    public static Timestamp dateUtilToTimestampSql(java.util.Date dateUtil) {
        return new Timestamp(dateUtil.getTime());
    }

    public static Time dateUtilToTimeSql(java.util.Date dateUtil) {
        return new Time(dateUtil.getTime());
    }
}
