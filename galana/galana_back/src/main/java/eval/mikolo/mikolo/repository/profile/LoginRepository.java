package eval.mikolo.mikolo.repository.profile;

import java.util.Optional;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import eval.mikolo.mikolo.model.produit.ResteStockProduit;
import eval.mikolo.mikolo.model.profile.Admin;
import eval.mikolo.mikolo.model.profile.Station;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class LoginRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Station> loginStation(String mail, String mdp) {
        Query query=entityManager.createNativeQuery(
            "select*from station where mail_station=:mail and mdp_station=:mdp", 
            Station.class
        );
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        List<Station> list=query.getResultList();
        if(list.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    public Optional<Station> findStationByMailAndRole(String mail, String role) {
        Query query=entityManager.createNativeQuery(
            "select*from station where mail_station=:mail and role=:role", 
            Station.class
        );
        query.setParameter("mail", mail);
        query.setParameter("role", role);
        List<Station> list=query.getResultList();
        if(list.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    public Optional<Station> findStationByMail(String mail) {
        Query query=entityManager.createNativeQuery(
            "select*from station where mail_station=:mail", 
            Station.class
        );
        query.setParameter("mail", mail);
        List<Station> list=query.getResultList();
        if(list.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    public Optional<Admin> findAdminByMailAndRole(String mail, String role) {
        Query query=entityManager.createNativeQuery(
            "select*from admin where mail_admin=:mail and role=:role", 
            Admin.class
        );
        query.setParameter("mail", mail);
        query.setParameter("role", role);
        List<Admin> list=query.getResultList();
        if(list.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    public Optional<Admin> findAdminByMail(String mail) {
        Query query=entityManager.createNativeQuery(
            "select*from admin where mail_admin=:mail", 
            Admin.class
        );
        query.setParameter("mail", mail);
        List<Admin> list=query.getResultList();
        if(list.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    public Optional<Admin> loginAdmin(String mail, String mdp) {
        Query query=entityManager.createNativeQuery(
            "select*from admin where mail_admin=:mail and mdp_admin=:mdp", 
            Admin.class
        );
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        List<Admin> list=query.getResultList();
        if(list.size()==0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    @Transactional
    public void saveStation(Station station) {
        if(station.getIdStation()==0) {
            entityManager.persist(station);
        } else {
            entityManager.merge(station);
        }
    }

    @Transactional
    public void saveAdmin(Admin admin) {
        if(admin.getIdAdmin()==0) {
            entityManager.persist(admin);
        } else {
            entityManager.merge(admin);
        }
    }
}
