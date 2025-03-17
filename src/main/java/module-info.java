module org.example.floskur {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.floskur to javafx.fxml;
    exports org.example.floskur;
}