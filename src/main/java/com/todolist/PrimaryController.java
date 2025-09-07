package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;

public class PrimaryController implements Initializable {
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
        cargarTareas();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTareas();
    }

    @FXML
    private void cargarTareas() {
        List<App.Tarea> listaTareas = App.getAllTasks();
        for (App.Tarea task : listaTareas) {
            idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombreColumna.setCellValueFactory(new PropertyValueFactory<>("name"));
            estadoColumna.setCellValueFactory(new PropertyValueFactory<>("isDone"));
            tablaTareas.getItems().add(task);
        }
    }

    @FXML
    private void irAEditar(MouseEvent event) {
        App.Tarea selectedItem = tablaTareas.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/todolist/editar.fxml"));
                Parent root = loader.load();

                // Get the controller and pass the selected task
                EditarController controller = loader.getController();
                controller.setTarea(selectedItem);

                Stage stage = new Stage();
                stage.setTitle("Editar Tarea");
                stage.setScene(new Scene(root));
                stage.show();

                // Close the current window if needed
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(String.format("Error al cargar la ventana de edición: %s", e.getMessage()));
            }
        }
    }
}
