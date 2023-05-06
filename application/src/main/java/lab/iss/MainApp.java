package lab.iss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lab.iss.business.Service;
import lab.iss.controllers.LoginController;
import lab.iss.repository.RepoDoctors;
import lab.iss.repository.RepoOrders;
import lab.iss.repository.RepoPharmacists;
import lab.iss.repository.UtilsDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainApp extends Application {

    private Service service;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        AnchorPane loginLayout = loader.load();
        Scene scene = new Scene(loginLayout);
        stage.setScene(scene);
        stage.setX(330);
        stage.setY(100);
        stage.show();

        initializeService();
        LoginController controller = loader.getController();
        controller.setService(service);
        controller.setStage(stage);
    }

    public void initializeService() throws IOException {
        FileInputStream inputStream = new FileInputStream("app.config");
        Properties properties = new Properties();
        properties.load(inputStream);

        String URL = properties.getProperty("URL");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        UtilsDB utils = new UtilsDB(URL, username, password);
        RepoDoctors repoDoctors = new RepoDoctors(utils);
        RepoPharmacists repoPharmacists = new RepoPharmacists(utils);
        RepoOrders repoOrders = new RepoOrders(utils);
        service = new Service(repoDoctors, repoPharmacists, repoOrders);
    }

    public static void main(String[] args) {
        launch();
    }
}