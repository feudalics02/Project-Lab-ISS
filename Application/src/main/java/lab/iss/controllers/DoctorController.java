package lab.iss.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lab.iss.business.Service;
import lab.iss.domain.Doctor;
import lab.iss.domain.Order;

import java.io.IOException;
import java.util.List;

public class DoctorController {

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private TableColumn<Order, Integer> columnOrders;

    @FXML
    private TableColumn<Order, String> columnMedicines;

    @FXML
    private TableColumn<Order, String> columnStatus;

    @FXML
    private Service service;

    private Doctor doctor;

    private Stage stage;

    public void setService(Service service) {
        this.service = service;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

        Platform.runLater(() -> {
            List<Order> orders = service.getOrders();
            tableOrders.setItems(FXCollections.observableArrayList(orders));
        });
    }

    public void logout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/lab/iss/login.fxml"));
            AnchorPane loginLayout = loader.load();
            Scene scene = new Scene(loginLayout);
            stage.setScene(scene);

            LoginController controller = loader.getController();
            controller.setService(service);
            controller.setStage(stage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
