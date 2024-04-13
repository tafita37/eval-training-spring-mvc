package eval.tsakitsaky.cinepax.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.tsakitsaky.cinepax.model.user.AdminUser;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
    public Optional<AdminUser> findByEmailAdminUserAndMdpAdminUser(String emailAdminUser, String mdpAdminUser);
}
