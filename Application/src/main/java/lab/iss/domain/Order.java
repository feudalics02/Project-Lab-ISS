package lab.iss.domain;

import javafx.util.Pair;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order extends AbsEntity<Integer> {

    @Column(name = "id_doctor")
    private int doctorID;

    @Column(name = "order_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<MedicinesOrder> medicines = new HashSet<>();

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Integer ID, LocalDateTime orderTime, OrderStatus status, int doctorID) {
        super(ID);
        this.orderTime = orderTime;
        this.status = status;
        this.doctorID = doctorID;
    }

    public Order() {
        super(1);

    }

    public String medicinesToString() {
        StringBuilder str = new StringBuilder();
        Iterator<MedicinesOrder> iter = medicines.iterator();
        while (iter.hasNext()) {
            MedicinesOrder medicine = iter.next();
            str.append(medicine.getMedicine().getName()).append(" x").append(medicine.getQuantity());
            if (iter.hasNext()) {
                str.append(", ");
            }
        }
        return str.toString();
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public Set<MedicinesOrder> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<MedicinesOrder> medicines) {
        this.medicines = medicines;
    }
}
