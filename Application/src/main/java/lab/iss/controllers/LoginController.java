package lab.iss.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lab.iss.business.Service;
import lab.iss.domain.Doctor;
import lab.iss.domain.Pharmacist;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField doctorUsername;

    @FXML
    private PasswordField doctorPassword;

    @FXML
    private TextField pharmacistUsername;

    @FXML
    private PasswordField pharmacistPassword;

    @FXML
    private Label errorMessageDoctor;

    @FXML
    private Label errorMessagePharmacist;

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void initialize() {
        errorMessageDoctor.setVisible(false);
        errorMessagePharmacist.setVisible(false);
    }

    public void loginDoctor() {
        String username = doctorUsername.getText();
        String password = doctorPassword.getText();

        Doctor doctor = service.loginDoctor(username, password);
        if (doctor != null) {
            showDoctorAccount(doctor);
        }
        else {
            errorMessageDoctor.setVisible(true);

            PauseTransition hideLabel = new PauseTransition(new Duration(3000));
            hideLabel.setOnFinished(event -> errorMessageDoctor.setVisible(false));
            hideLabel.play();
        }
    }

    public void loginPharmacist() {
        String username = pharmacistUsername.getText();
        String password = pharmacistPassword.getText();

        Pharmacist pharmacist = service.loginPharmacist(username, password);
        if (pharmacist != null) {
            showPharmacistAccount(pharmacist);
        }
        else {
            errorMessagePharmacist.setVisible(true);

            PauseTransition hideLabel = new PauseTransition(new Duration(3000));
            hideLabel.setOnFinished(event -> errorMessagePharmacist.setVisible(false));
            hideLabel.play();
        }
    }

    public void showDoctorAccount(Doctor doctor) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/lab/iss/doctor.fxml"));
            AnchorPane loginLayout = loader.load();
            Scene scene = new Scene(loginLayout);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            DoctorController controller = loader.getController();
            controller.setService(service);
            controller.setDoctor(doctor);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPharmacistAccount(Pharmacist pharmacist) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/lab/iss/pharmacist.fxml"));
            AnchorPane loginLayout = loader.load();
            Scene scene = new Scene(loginLayout);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            PharmacistController controller = loader.getController();
            controller.setService(service);
            controller.setPharmacist(pharmacist);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}