package org.example.pokemon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button btnCreateTeam;

    @FXML
    private void handleStart() {
        System.out.println("Start Button Pressed");
    }
}