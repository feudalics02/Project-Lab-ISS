module lab.iss {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens lab.iss to javafx.fxml;
    exports lab.iss;
    exports lab.iss.controllers;
    exports lab.iss.domain;
    exports lab.iss.business;
    exports lab.iss.repository;
    opens lab.iss.controllers to javafx.fxml;
}