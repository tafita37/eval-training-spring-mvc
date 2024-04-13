package eval.tsakitsaky.cinepax.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.tsakitsaky.cinepax.model.user.AdminUser;
import eval.tsakitsaky.cinepax.model.user.Etudiant;
import eval.tsakitsaky.cinepax.repository.user.AdminUserRepository;
import eval.tsakitsaky.cinepax.repository.user.EtudiantRepository;

@Service
public class UserService {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    AdminUserRepository adminUserRepository;

    public Etudiant loginEtudiant(String emailEtudiant, String mdpEtudiant)
    throws Exception {
        return etudiantRepository.findByEmailEtudiantAndMdpEtudiant(emailEtudiant, mdpEtudiant).get();
    }

    public AdminUser loginAdminUser(String emailAdminUser, String mdpAdminUser) {
        return adminUserRepository.findByEmailAdminUserAndMdpAdminUser(emailAdminUser, mdpAdminUser).get();
    }
}
