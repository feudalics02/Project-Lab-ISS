package lab.iss.repository;

import lab.iss.domain.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RepoDoctors implements IRepoDB<Doctor, Integer> {

    private final SessionFactory sessionFactory;

    public RepoDoctors(Configuration configuration) {
        sessionFactory = configuration.buildSessionFactory();
    }


    @Override
    public int add(Doctor doctor) {
        return 0;
    }

    @Override
    public void remove(Integer integer) {

    }

    public Doctor getByUsername(String username) {
        Doctor doctor = null;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Doctor> doctors = session.createQuery("SELECT d FROM Doctor d WHERE d.username = :username", Doctor.class)
                            .setParameter("username", username).list();
            if (doctors.size() > 0) {
                doctor = doctors.get(0);
            }
            session.getTransaction().commit();
        }

        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            doctors = session.createQuery("SELECT d FROM Doctor d", Doctor.class).list();
            session.getTransaction().commit();
        }

        return doctors;
    }
}
