package lab.iss.business;

import lab.iss.domain.*;
import lab.iss.domain.Observable;
import lab.iss.domain.Observer;
import lab.iss.repository.RepoDoctors;
import lab.iss.repository.RepoMedicines;
import lab.iss.repository.RepoOrders;
import lab.iss.repository.RepoPharmacists;

import java.time.LocalDateTime;
import java.util.*;


public class Service implements Observable {

    private final RepoDoctors repoDoctors;

    private final RepoPharmacists repoPharmacists;

    private final RepoOrders repoOrders;

    private final RepoMedicines repoMedicines;

    private List<Observer> observers = new ArrayList<>();

    public Service(RepoDoctors repoDoctors, RepoPharmacists repoPharmacists, RepoOrders repoOrders, RepoMedicines repoMedicines) {
        this.repoDoctors = repoDoctors;
        this.repoPharmacists = repoPharmacists;
        this.repoOrders = repoOrders;
        this.repoMedicines = repoMedicines;
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
        return repoOrders.getByDepartment(departmentID);
    }

    public List<Order> getOrders() {
        List<Order> orders = repoOrders.getAll();
        orders.sort(Comparator.comparing(Order::getID));
        return orders;
    }

    public void changeStatus(int orderID, OrderStatus status) {
        repoOrders.setStatus(orderID, status);
        notifyObservers();
    }

    public Map<String, Integer> getMedicines(String department) {
        List<Order> orders = repoOrders.getByDepartment(department);
        Map<String, Integer> medicines = new HashMap<>();

        for (Order order : orders) {
            for (MedicinesOrder med : order.getMedicines()) {
                String name = med.getMedicine().getName();
                int quantity = med.getQuantity();
                if (medicines.containsKey(name)) {
                    int newQuantity = medicines.get(name) + quantity;
                    medicines.put(name, newQuantity);
                }
                else {
                    medicines.put(name, quantity);
                }
            }
        }

        return medicines;
    }

    public List<Medicine> getAllMedicines() {
        return repoMedicines.getAll();
    }

    public void placeOrder(int doctorID, List<Medicine> medicines) {
        Order order = new Order(1, LocalDateTime.now(), OrderStatus.PLACED, doctorID);
        int orderID = repoOrders.add(order);
        order.setID(orderID);

        boolean valid = false;
        for (Medicine medicine : medicines) {
            int quantity;
            try {
                quantity = Integer.parseInt(medicine.getQuantityField().getText());
                if (quantity < 1) {
                    continue;
                }
            }
            catch (Exception ex) {
                continue;
            }
            MedicinesOrder medicinesOrder = new MedicinesOrder(1, order, medicine, quantity);
            repoOrders.addMedicine(medicinesOrder);
            valid = true;
        }

        if (!valid) {
            repoOrders.remove(orderID);
        }
        notifyObservers();
    }

    public void updateOrder(Order order, List<Medicine> medicines) {
        for (Medicine medicine : medicines) {
            int quantity;
            try {
                quantity = Integer.parseInt(medicine.getQuantityField().getText());
                if (quantity < 1) {
                    continue;
                }
            }
            catch (Exception ex) {
                continue;
            }

            MedicinesOrder medFound = repoOrders.getMedicineOrder(order.getID(), medicine.getID());
            if (medFound == null) {
                MedicinesOrder medicinesOrder = new MedicinesOrder(1, order, medicine, quantity);
                repoOrders.addMedicine(medicinesOrder);
            }
            else {
                int oldQuantity = medFound.getQuantity();
                medFound.setQuantity(oldQuantity + quantity);
                repoOrders.updateMedicine(medFound);
            }
        }
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
