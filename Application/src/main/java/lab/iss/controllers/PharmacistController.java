package lab.iss.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab.iss.business.Service;
import lab.iss.domain.Observer;
import lab.iss.domain.Order;
import lab.iss.domain.OrderStatus;
import lab.iss.domain.Pharmacist;

import java.util.List;
import java.util.Map;

public class PharmacistController implements Observer {

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private TableColumn<Order, Integer> columnOrders;

    @FXML
    private TableColumn<Order, String> columnMedicines;

    @FXML
    private TableColumn<Order, String> columnStatus;

    @FXML
    private TextField departmentField;

    @FXML
    private TableView<Map.Entry<String, Integer>> reportTable;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> nameColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> quantityColumn;

    private Service service;

    private Pharmacist pharmacist;

    public void setService(Service service) {
        this.service = service;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
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
            Map.Entry<String, Integer> entry = data.getValue();
            return new SimpleStringProperty(entry.getKey());
        });

        quantityColumn.setCellValueFactory(data -> {
            Map.Entry<String, Integer> entry = data.getValue();
            return new SimpleIntegerProperty(entry.getValue()).asObject();
        });

        refreshTable();
    }

    public void refreshTable() {
        Platform.runLater(() -> {
            List<Order> orders = service.getOrders();
            tableOrders.setItems(FXCollections.observableArrayList(orders));
        });
    }

    public void generateReport() {
        String department = departmentField.getText();
        if (!department.equals("")) {
            Map<String, Integer> medicines = service.getMedicines(department);
            reportTable.setItems(FXCollections.observableArrayList(medicines.entrySet()));
        }
    }

    public void prepareOrder() {
        if (tableOrders.getSelectionModel().getSelectedItems().size() > 0) {
            Order order = tableOrders.getSelectionModel().getSelectedItem();
            if (order.getStatus() == OrderStatus.PLACED) {
                service.changeStatus(order.getID(), OrderStatus.PREPARED);
            }
        }
    }

    public void deliverOrder() {
        if (tableOrders.getSelectionModel().getSelectedItems().size() > 0) {
            Order order = tableOrders.getSelectionModel().getSelectedItem();
            if (order.getStatus() == OrderStatus.PREPARED) {
                service.changeStatus(order.getID(), OrderStatus.DELIVERED);
            }
        }
    }

    public void logout() {
        Stage stage = (Stage) tableOrders.getScene().getWindow();
        stage.close();
        service.removeObserver(this);
    }

    @Override
    public void update() {
        refreshTable();
    }
}
