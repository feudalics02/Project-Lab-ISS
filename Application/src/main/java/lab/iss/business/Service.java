package lab.iss.business;

import lab.iss.domain.Doctor;
import lab.iss.domain.Order;
import lab.iss.domain.Pharmacist;
import lab.iss.repository.RepoDoctors;
import lab.iss.repository.RepoOrders;
import lab.iss.repository.RepoPharmacists;

import java.util.List;


public class Service {

    private final RepoDoctors repoDoctors;

    private final RepoPharmacists repoPharmacists;

    private final RepoOrders repoOrders;

    public Service(RepoDoctors repoDoctors, RepoPharmacists repoPharmacists, RepoOrders repoOrders) {
        this.repoDoctors = repoDoctors;
        this.repoPharmacists = repoPharmacists;
        this.repoOrders = repoOrders;
    }

    public Doctor loginDoctor(String username, String password) {
        Doctor doctor = repoDoctors.getByUsername(username);
        if (doctor != null && doctor.getPassword().equals(password)) {
            return doctor;
        }
        return null;
    }

    public Pharmacist loginPharmacist(String username, String password) {
        Pharmacist pharmacist = repoPharmacists.getByUsername(username);
        if (pharmacist != null && pharmacist.getPassword().equals(password)) {
            return pharmacist;
        }
        return null;
    }

    public List<Order> getOrdersForDepartment(int departmentID) {
        List<Order> orders = repoOrders.getByDepartment(departmentID);
        for (Order order : orders) {
            order.setMedicines(repoOrders.getMedicines(order.getID()));
        }
        return orders;
    }

    public List<Order> getOrders() {
        List<Order> orders = repoOrders.getAll();
        for (Order order : orders) {
            order.setMedicines(repoOrders.getMedicines(order.getID()));
        }
        return orders;
    }
}
