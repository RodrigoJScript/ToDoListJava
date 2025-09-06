module com.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;

    opens com.todolist to javafx.fxml;

    exports com.todolist;
}
