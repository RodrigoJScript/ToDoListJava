package com.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
        configDatabase();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/todolist/primary.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    private static final String URL = "jdbc:sqlite:todolist.db";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Conexión a SQLite exitosa!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQLite: " + e.getMessage());
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks ( id integer PRIMARY KEY AUTOINCREMENT, name text NOT NULL, isDone integer)";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void configDatabase() {
        connect();
        createTable();
    }

}