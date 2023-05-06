module lab.iss {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab.iss to javafx.fxml;
    exports lab.iss;
    exports lab.iss.controllers;
    opens lab.iss.controllers to javafx.fxml;
}