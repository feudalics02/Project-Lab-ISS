package lab.iss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lab.iss.business.Service;
import lab.iss.controllers.LoginController;
import lab.iss.repository.RepoDoctors;
import lab.iss.repository.RepoMedicines;
import lab.iss.repository.RepoOrders;
import lab.iss.repository.RepoPharmacists;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class MainApp extends Application {

    private Service service;

    @Override
    public void start(Stage stage) throws IOException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        AnchorPane loginLayout = loader.load();
        Scene scene = new Scene(loginLayout);
        stage.setScene(scene);
        stage.show();

        initializeService();
        LoginController controller = loader.getController();
        controller.setService(service);
    }

    public void initializeService() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        RepoDoctors repoDoctors = new RepoDoctors(configuration);
        RepoPharmacists repoPharmacists = new RepoPharmacists(configuration);
        RepoOrders repoOrders = new RepoOrders(configuration);
        RepoMedicines repoMedicines = new RepoMedicines(configuration);
        service = new Service(repoDoctors, repoPharmacists, repoOrders, repoMedicines);
    }

    public static void main(String[] args) {
        launch();
    }
}