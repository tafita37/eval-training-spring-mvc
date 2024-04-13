package eval.tsakitsaky.cinepax.config;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.ServletContext;

@Service
public class UploadFileService {
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    ServletContext servletContext;

    // public void uploadFile(MultipartFile file, String chemin) throws IOException {
    //     // Obtenir le répertoire de destination depuis le chemin absolu
    //     System.out.println("Path : "+servletContext.getRealPath(chemin));
    //     File destinationDir = new File(servletContext.getRealPath(chemin));

    //     // Vérifier si le répertoire de destination existe, sinon le créer
    //     if (!destinationDir.exists()) {
    //         destinationDir.mkdirs();
    //     }

    //     // Créer le chemin complet pour le fichier de destination
    //     Path destinationFile = Paths.get(destinationDir.getPath(), file.getOriginalFilename());

    //     // Copier le fichier vers le répertoire de destination
    //     Files.copy(file.getInputStream(), destinationFile);
    // }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image");
    }

    public String uploadFile(MultipartFile file, String chemin) 
    throws Exception {
        if(!this.isImage(file)) {
            throw new Exception("Vous devez uploader une photo");
        }
        String realPath = servletContext.getRealPath(chemin);
        Resource resource = new FileSystemResource(realPath);
        String originalFileName=file.getOriginalFilename();
        LocalDateTime now=LocalDateTime.now();
        String newNamePicture = (
                                    originalFileName.substring(0, originalFileName.lastIndexOf("."))+
                                    Timestamp.valueOf(now)+
                                    originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length())
                                )
                                .replace(":", "")
                                .replace(" ", "");
        Resource uploadedFile = resource.createRelative(newNamePicture);
        uploadedFile.getFile().getName();
        file.transferTo(uploadedFile.getFile());
        return newNamePicture;
    }
}
