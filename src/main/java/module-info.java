module com.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens com.todolist to javafx.fxml;

    exports com.todolist;
}
