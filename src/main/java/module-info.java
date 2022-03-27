module com.ciphers.enddec {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.ciphers.enddec to javafx.fxml;
    exports com.ciphers.enddec;
}