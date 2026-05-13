module universite_paris8iut.bcottinet.test {
    requires javafx.controls;
    requires javafx.fxml;


    opens universite_paris8iut.bcottinet.app to javafx.fxml;
    exports universite_paris8iut.bcottinet.app;
}