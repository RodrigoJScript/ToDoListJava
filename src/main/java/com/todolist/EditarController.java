package com.todolist;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class EditarController implements Initializable {
    @FXML
    private TextField campoTexto;

    @FXML
    private Button editarButton;

    @FXML
    private CheckBox checkBoxDone;

    @FXML
    private Label lblNombre;

    @FXML
    private AnchorPane raiz;

    private App.Tarea tareaActual;

    public void setTarea(App.Tarea tarea) {
        this.tareaActual = tarea;
        if (tarea != null) {
            lblNombre.setText(tarea.getName());
            campoTexto.setText(tarea.getName());
            checkBoxDone.setSelected(tarea.getIsDone());
        }
    }

    @FXML
    private void editarTarea() {
        if (tareaActual != null) {
            String nuevoNombre = campoTexto.getText().trim();
            if (!nuevoNombre.isEmpty()) {
                tareaActual.setName(nuevoNombre);
                tareaActual.setIsDone(checkBoxDone.isSelected());
                App.updateTask(tareaActual);

                // Cerrar la ventana actual
                Stage stage = (Stage) raiz.getScene().getWindow();
                stage.close();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
