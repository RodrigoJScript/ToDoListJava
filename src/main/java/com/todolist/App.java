package com.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        createTable();
        launch(args);
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
        String sql = "CREATE TABLE IF NOT EXISTS tasks ( id integer PRIMARY KEY AUTOINCREMENT, name text NOT NULL, isDone boolean)";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTask(String name, Boolean isDone) {
        String sql = "INSERT INTO tasks (name, isDone) VALUES (?, ?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setBoolean(2, isDone);
            pstmt.executeUpdate();
            System.out.println("Tarea agregada exitosamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Tarea> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            List<Tarea> listaTareas = new ArrayList<>();
            while (rs.next()) {
                listaTareas.add(new Tarea(rs.getInt("id"), rs.getString("name"), rs.getBoolean("isDone")));
            }
            return listaTareas;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static class Tarea {
        private int id;
        private String name;
        private boolean isDone;

        public Tarea(int id, String name, boolean isDone) {
            this.id = id;
            this.name = name;
            this.isDone = isDone;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean getIsDone() {
            return isDone;
        }

        public void setIsDone(boolean isDone) {
            this.isDone = isDone;
        }
    }

    public static void updateTask(Tarea tarea) {
        String sql = "UPDATE tasks SET name = ?, isDone = ? WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tarea.getName());
            pstmt.setBoolean(2, tarea.getIsDone());
            pstmt.setInt(3, tarea.getId());
            pstmt.executeUpdate();
            System.out.println("Tarea actualizada exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la tarea: " + e.getMessage());
        }
    }

    public static void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Tarea eliminada exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la tarea: " + e.getMessage());
        }
    }
}