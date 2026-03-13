module org.example.pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.pokemon to javafx.fxml;
    opens org.example.pokemon.logic to javafx.fxml;
    opens org.example.pokemon.Manager to javafx.fxml;
    opens org.example.pokemon.model to javafx.fxml;

    exports org.example.pokemon;
    exports org.example.pokemon.logic;
    exports org.example.pokemon.Manager;
    exports org.example.pokemon.model;
}