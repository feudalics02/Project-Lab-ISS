package lab.iss.repository;

import lab.iss.domain.Pharmacist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RepoPharmacists implements IRepoDB<Pharmacist, Integer> {

    private final SessionFactory sessionFactory;

    public RepoPharmacists(Configuration configuration) {
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public int add(Pharmacist pharmacist) {
        return 0;
    }

    @Override
    public void remove(Integer integer) {

    }

    public Pharmacist getByUsername(String username) {
        Pharmacist pharmacist = null;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Pharmacist> pharmacists = session.createQuery("SELECT p FROM Pharmacist p WHERE p.username = :username", Pharmacist.class)
                    .setParameter("username", username).list();
            if (pharmacists.size() > 0) {
                pharmacist = pharmacists.get(0);
            }
            session.getTransaction().commit();
        }

        return pharmacist;
    }

    @Override
    public List<Pharmacist> getAll() {
        List<Pharmacist> pharmacists;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            pharmacists = session.createQuery("SELECT p FROM Pharmacist p", Pharmacist.class).list();
            session.getTransaction().commit();
        }

        return pharmacists;
    }
}
