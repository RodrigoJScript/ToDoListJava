package com.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private TextField campoTexto;

    @FXML
    private void agregarTarea() {
        String tarea = campoTexto.getText().trim();
        if (tarea.isEmpty()) {
            return;
        }
        App.insertTask(tarea, false);
        campoTexto.clear();
        App.getAllTasks();
    }
}
