package lab.iss.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lab.iss.business.Service;
import lab.iss.domain.Doctor;
import lab.iss.domain.Medicine;
import lab.iss.domain.Observer;
import lab.iss.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class DoctorController implements Observer {

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private TableColumn<Order, Integer> columnOrders;

    @FXML
    private TableColumn<Order, String> columnMedicines;

    @FXML
    private TableColumn<Order, String> columnStatus;

    @FXML
    private TableView<Medicine> tableMedicines;

    @FXML
    private TableColumn<Medicine, String> nameColumn;

    @FXML
    private TableColumn<Medicine, String> quantityColumn;

    @FXML
    private TableColumn<Medicine, String> selectedColumn;

    private Service service;

    private Doctor doctor;

    public void setService(Service service) {
        this.service = service;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void initialize() {
        columnOrders.setCellValueFactory(data -> {
            Order order = data.getValue();
            return new SimpleIntegerProperty(order.getID()).asObject();
        });

        columnMedicines.setCellValueFactory(data -> {
            Order order = data.getValue();
            return new SimpleStringProperty(order.medicinesToString());
        });

        columnStatus.setCellValueFactory(data -> {
            Order order = data.getValue();
            return new SimpleStringProperty(order.getStatus().toString());
        });

        nameColumn.setCellValueFactory(data -> {
            Medicine medicine = data.getValue();
            return new SimpleStringProperty(medicine.getName());
        });

        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityField"));

        selectedColumn.setCellValueFactory(new PropertyValueFactory<>("checkedField"));
        selectedColumn.setStyle("-fx-alignment: CENTER");

        refreshTableOrders();
        Platform.runLater(() -> {
            List<Medicine> medicines = service.getAllMedicines();
            tableMedicines.setItems(FXCollections.observableArrayList(medicines));
        });
    }

    public void refreshTableOrders() {
        Platform.runLater(() -> {
            List<Order> orders = service.getOrdersForDepartment(doctor.getDepartment());
            tableOrders.setItems(FXCollections.observableArrayList(orders));
        });
    }

    public void placeOrder() {
        List<Medicine> medicines = tableMedicines.getItems();
        List<Medicine> order = new ArrayList<>();
        for (Medicine med : medicines) {
            if (med.getCheckedField().isSelected()) {
                order.add(med);
            }
        }

        if (order.size() > 0) {
            service.placeOrder(doctor.getID(), order);
        }
    }

    public void addToOrder() {
        if (tableOrders.getSelectionModel().getSelectedItems().size() > 0) {
            Order order = tableOrders.getSelectionModel().getSelectedItem();
            List<Medicine> medicines = tableMedicines.getItems();
            service.updateOrder(order, medicines);
        }
    }

    public void logout() {
        Stage stage = (Stage) tableOrders.getScene().getWindow();
        stage.close();
        service.removeObserver(this);
    }

    @Override
    public void update() {
        refreshTableOrders();
    }

}
