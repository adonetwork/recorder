package com.adonetwork.recorder.application.views;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adonetwork.recorder.application.controllers.MainController;
import com.adonetwork.recorder.application.controllers.RecorderController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Configuration
@Slf4j
public class ControllerConfiguration {
    
      /**
     * Classe utilitaire de gestion des vues et des controlleurs associés
     * @author adonorio
     */

     // Liste des membres
     @Bean(name = "mainView")
     public ViewHolder getMainView() throws IOException {
        log.info("Chargement de l'interface principale de l'application");
        return loadView("fxml/mainView.fxml");
     }

     @Bean
     public MainController getMainController() throws IOException {
         return (MainController) getMainView().getController();
     }

     @Bean(name = "recorderView")
     public ViewHolder getRecorderView() throws IOException {
        log.info("Chargement de la vue d'enregistrement de la liste de lecture");
        return loadView("fxml/recorderView.fxml");
     }

     @Bean
     public RecorderController getRecordController() throws IOException {
         return (RecorderController) getRecorderView().getController();
     }

     // Liste des méthodes

    /**
     * Chargement d'une vue FXML
     * @param url le lien vers le fichier FXML de la vue
     * @return Wrapper sur la vue sélectionné
     * @throws IOException Exception levée si une erreur survient lors du chargement de la vue.
     */
    protected ViewHolder loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(fxmlStream);

            return new ViewHolder(root, loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }
}
