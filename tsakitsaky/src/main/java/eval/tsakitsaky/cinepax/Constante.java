package eval.tsakitsaky.cinepax;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;

public class Constante {
    public static Time toTimeOfTimestamp(Timestamp timestamp) {
        long timeInMillis = timestamp.getTime();

        // Créer un nouvel objet java.sql.Time à partir du temps en millisecondes
        Time time = new Time(timeInMillis);

        // Afficher la partie Time
        System.out.println("Partie Time de Timestamp : " + time);
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

    public static String getHTMLPDFTemplate(String contenu, String baseUrl, double widthSize, String h1Title) {
        return "<!DOCTYPE html>"+
                "<html lang=\"en\">"+
                    "<head>"+
                        "<meta charset=\"UTF-8\"/>"+
                        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>"+
                        "<title>Skydash Admin</title>"+
                        // "<link rel=\"stylesheet\" href=\""+baseUrl+"/assets/css/pdf.css\"/>"+
                    "</head>"+
                    "<body>"+
                        // "<div  style=\"width : "+widthSize+"px;\">"+
                            "<h1>"+h1Title+"</h1>"+
                            contenu+
                        // "</div>"+
                    "</body>"+
                "</html>";
    }

    public static void uploadFie(MultipartFile file, String chemin)
    throws Exception {
        System.out.println(file.getOriginalFilename());
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            
            // Créer un objet Path pour le répertoire de destination
            Path destinationPath = Paths.get(chemin);
            System.out.println(destinationPath.toString());

            // Vérifier si le répertoire de destination existe, sinon le créer
            if (!Files.exists(destinationPath)) {
                Files.createDirectories(destinationPath);
            }

            // Créer le chemin complet pour le fichier de destination
            Path destinationFile = Paths.get(chemin, file.getOriginalFilename());

            // Copier le fichier vers le répertoire de destination
            Files.write(destinationFile, bytes);
        }
    }
}
