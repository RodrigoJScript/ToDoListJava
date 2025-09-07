package com.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class EditarController {
    @FXML
    private TextField campoTexto;

    @FXML
    private Button editarButton;

    @FXML
    private void editarTarea() {
        String tarea = campoTexto.getText().trim();
    }
}
