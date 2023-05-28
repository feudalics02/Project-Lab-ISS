package lab.iss.domain;

import javax.persistence.*;

@Entity
@Table(name = "medicines_order")
public class MedicinesOrder extends AbsEntity<Integer> {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_medicine")
    private Medicine medicine;

    private int quantity;

    public MedicinesOrder(Integer ID, Order order, Medicine medicine, int quantity) {
        super(ID);
        this.order = order;
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public MedicinesOrder() {
        super(1);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
