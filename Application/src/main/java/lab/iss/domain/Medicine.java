package lab.iss.domain;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicines")
public class Medicine extends AbsEntity<Integer> {

    private String name;

    private String description;

    @OneToMany(mappedBy = "medicine")
    private Set<MedicinesOrder> orders = new HashSet<>();

    @Transient
    private TextField quantityField = new TextField();

    @Transient
    private CheckBox checkedField = new CheckBox();

    public Medicine(Integer ID, String name, String description, Set<MedicinesOrder> orders) {
        super(ID);
        this.name = name;
        this.description = description;
        this.orders = orders;
    }

    public Medicine() {
        super(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MedicinesOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<MedicinesOrder> orders) {
        this.orders = orders;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(TextField quantityField) {
        this.quantityField = quantityField;
    }

    public CheckBox getCheckedField() {
        return checkedField;
    }

    public void setCheckedField(CheckBox checkedField) {
        this.checkedField = checkedField;
    }
}
