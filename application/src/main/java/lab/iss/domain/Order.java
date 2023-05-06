package lab.iss.domain;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order extends Entity<Integer> {

    private LocalDateTime orderTime;

    private List<Pair<Medicine, Integer>> medicines;

    private OrderStatus status;

    public Order(Integer ID, LocalDateTime orderTime, OrderStatus status) {
        super(ID);
        this.orderTime = orderTime;
        this.status = status;
        medicines = new ArrayList<>();
    }

    public String medicinesToString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < medicines.size(); i++) {
            str.append(medicines.get(i).getKey().getName()).append(" x").append(medicines.get(i).getValue());
            if (i < medicines.size() - 1) {
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

    public List<Pair<Medicine, Integer>> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Pair<Medicine, Integer>> medicines) {
        this.medicines = medicines;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
