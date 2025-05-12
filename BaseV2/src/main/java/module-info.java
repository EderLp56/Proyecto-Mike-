module mx.unam.aragon.v {
    requires javafx.controls;
    requires javafx.fxml;
    requires jlayer;

    opens mx.unam.aragon to javafx.fxml;
    exports mx.unam.aragon;

}