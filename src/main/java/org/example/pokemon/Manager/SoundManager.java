package org.example.pokemon.Manager;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class SoundManager {

    public static void play(String fileName) {
        try {
            URL resource = SoundManager.class.getResource("/org/example/pokemon/sounds/" + fileName);

            if (resource != null) {
                AudioClip clip = new AudioClip(resource.toExternalForm());
                clip.play(1.0);

                System.out.println("Son joué : " + fileName);
            } else {
                System.err.println("Fichier introuvable : " + fileName);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }
}