package com.todolist;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrimaryController {
    @FXML
    private TextField campoTexto;

    @FXML
    private TableView<App.Tarea> tablaTareas;

    @FXML
    private TableColumn<App.Tarea, Integer> idColumna;

    @FXML
    private TableColumn<App.Tarea, String> nombreColumna;

    @FXML
    private TableColumn<App.Tarea, Boolean> estadoColumna;

    @FXML
    private void agregarTarea() {
        String tarea = campoTexto.getText().trim();
        if (tarea.isEmpty()) {
            return;
        }
        App.insertTask(tarea, false);
        campoTexto.clear();
        List<App.Tarea> listaTareas = App.getAllTasks();
        for (App.Tarea task : listaTareas) {
            idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombreColumna.setCellValueFactory(new PropertyValueFactory<>("name"));
            estadoColumna.setCellValueFactory(new PropertyValueFactory<>("isDone"));

            tablaTareas.getItems().add(task);
        }
    }
}
