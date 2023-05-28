package lab.iss.repository;

import lab.iss.domain.MedicinesOrder;
import lab.iss.domain.Order;
import lab.iss.domain.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RepoOrders implements IRepoDB<Order, Integer> {

    private final SessionFactory sessionFactory;

    public RepoOrders(Configuration configuration) {
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public int add(Order order) {
        int generatedID;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            generatedID = (Integer) session.save(order);
            session.getTransaction().commit();
        }
        return generatedID;
    }

    public void addMedicine(MedicinesOrder medicine) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(medicine);
            session.getTransaction().commit();
        }
    }

    public MedicinesOrder getMedicineOrder(int orderID, int medicineID) {
        MedicinesOrder medicinesOrder = null;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<MedicinesOrder> list = session.createQuery("SELECT m FROM MedicinesOrder m WHERE " +
                            "m.order.id = :orderID AND m.medicine.id = :medicineID", MedicinesOrder.class)
                            .setParameter("orderID", orderID).setParameter("medicineID", medicineID).list();
            if (list.size() > 0) {
                medicinesOrder = list.get(0);
            }
            session.getTransaction().commit();
        }

        return medicinesOrder;
    }

    public void updateMedicine(MedicinesOrder medicinesOrder) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(medicinesOrder);
            session.getTransaction().commit();
        }
    }

    @Override
    public void remove(Integer ID) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Order order = session.get(Order.class, ID);
            session.delete(order);

            session.getTransaction().commit();
        }
    }

    public void setStatus(int orderID, OrderStatus status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, orderID);
            order.setStatus(status);
            session.update(order);
            session.getTransaction().commit();
        }
    }

    public List<Order> getByDepartment(int departmentID) {
        List<Order> orders;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            String query = "SELECT o FROM Order o WHERE o.doctorID IN (SELECT d.id FROM Doctor d WHERE d.departmentID = :id)";
            orders = session.createQuery(query, Order.class)
                        .setParameter("id", departmentID).getResultList();

            session.getTransaction().commit();
        }

        return orders;
    }

    public List<Order> getByDepartment(String department) {
        List<Order> orders;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            String query = "SELECT o FROM Order o WHERE o.doctorID IN (SELECT d.id FROM Doctor d WHERE d.departmentID IN " +
                    "(SELECT dep.id FROM Department dep WHERE dep.name = :department))";
            orders = session.createQuery(query, Order.class)
                    .setParameter("department", department).getResultList();

            session.getTransaction().commit();
        }

        return orders;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            orders = session.createQuery("SELECT o FROM Order o", Order.class).list();
            session.getTransaction().commit();
        }

        return orders;
    }
}
