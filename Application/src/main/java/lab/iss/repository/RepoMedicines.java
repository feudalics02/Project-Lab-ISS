package lab.iss.repository;

import lab.iss.domain.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RepoMedicines implements IRepoDB<Medicine, Integer> {

    private final SessionFactory sessionFactory;

    public RepoMedicines(Configuration configuration) {
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public int add(Medicine medicine) {
        return 0;
    }

    @Override
    public void remove(Integer integer) {

    }

    @Override
    public List<Medicine> getAll() {
        List<Medicine> medicines;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            medicines = session.createQuery("SELECT m FROM Medicine m", Medicine.class).list();
            session.getTransaction().commit();
        }

        return medicines;
    }
}
