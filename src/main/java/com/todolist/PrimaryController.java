package com.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private TextField campoTexto;

    @FXML
    private void agregarTarea() {
        String tarea = campoTexto.getText();
        System.out.println(tarea);
        campoTexto.clear();
    }
}
