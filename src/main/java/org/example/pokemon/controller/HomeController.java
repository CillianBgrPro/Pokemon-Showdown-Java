package org.example.pokemon.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btnCreateTeam;

    @FXML
    private void handleStart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pokemon/view/team-builder-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCreateTeam.getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
        }
    }
}