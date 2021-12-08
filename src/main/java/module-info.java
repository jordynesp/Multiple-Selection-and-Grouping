module com.example.a4basics {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.a4 to javafx.fxml;
    exports com.example.a4;
}