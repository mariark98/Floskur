module org.example.floskur {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens Vinnsla to com.fasterxml.jackson.databind;
    opens org.example.floskur to javafx.fxml;
    exports org.example.floskur;
}