module principal.lojamvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens controller to javafx.fxml,javafx.base;
        
    exports principal;
}
